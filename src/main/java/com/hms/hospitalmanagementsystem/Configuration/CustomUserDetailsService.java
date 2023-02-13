package com.hms.hospitalmanagementsystem.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Comment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hms.hospitalmanagementsystem.Entity.UserLogin;
import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.RepositoryImpl.UserLoginRepositoryImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService{

    private UserLoginRepositoryImpl userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles=null;
        List<Map<String, Object>> userlist =new ArrayList<>();
            try {
                userlist=userLoginRepository.findByUserName(username);
                System.out.println(userlist);
            } catch (BadRequestException e) {
                e.printStackTrace();
            }

            Map<String,Object> user=userlist.get(0);
            if(user !=null){
               
               roles=Arrays.asList(new SimpleGrantedAuthority(user.get("authority").toString().toUpperCase()));
                return new User(user.get("username").toString(),user.get("password").toString(),roles);
            }
            throw new UsernameNotFoundException("user not found");
           
       
    }
    
}
