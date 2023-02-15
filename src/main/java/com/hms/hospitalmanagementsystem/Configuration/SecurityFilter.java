package com.hms.hospitalmanagementsystem.Configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;




@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityFilter {

   private final CustomAuthenticationFilter authenticationFilter;
   private final CustomUserDetailsService customUserDetailsService;

   

   @Bean
   HttpStatusEntryPoint httpStatusEntryPoint(){
    return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

   return http
    .csrf().disable()
    .authorizeHttpRequests()
    .requestMatchers("/api/user/token").permitAll()
    .anyRequest()
    .authenticated()
    .and().exceptionHandling()
    
    .authenticationEntryPoint(httpStatusEntryPoint())
    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and().addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
    .authenticationProvider(daoAuthenticationProvider())
    .build();

    
   
        

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
   public  DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    
}
