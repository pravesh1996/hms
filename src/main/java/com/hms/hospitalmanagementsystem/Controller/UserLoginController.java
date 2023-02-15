package com.hms.hospitalmanagementsystem.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hospitalmanagementsystem.Configuration.CustomUserDetailsService;
import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Repository.UserLoginRepository;
import com.hms.hospitalmanagementsystem.Request.AuthenticationReq;
import com.hms.hospitalmanagementsystem.Service.UserLoginService;
import com.hms.hospitalmanagementsystem.Util.JwtUtil;

import lombok.AllArgsConstructor;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserLoginController {

    private final UserLoginService loginService;
    private final UserLoginRepository loginRepository;
    private AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private CustomUserDetailsService customUserDetailsService;
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Map<String, Object>> getUserLogin(Principal principal) {

        return loginService.getAll(principal).stream().map(d -> {
            Map<String, Object> _map = new HashMap<>();
            _map.put("username", d.get("username").toString());
            _map.put("mobileno", d.get("mobileNo").toString());
            return _map;
        }).collect(Collectors.toList());
    }

    @PostMapping("/token")
    public Map<String, Object> generateToken(@RequestBody AuthenticationReq authenticationReq)
            throws BadRequestException {
        
        List<Map<String, Object>> username = new ArrayList<>();
        username = loginRepository.findByUserName(authenticationReq.getUsername());
       
        if (username.isEmpty()) {
            throw new BadRequestException("Invalid username or password");
        }

        boolean password = encoder.matches(authenticationReq.getPassword(), username.get(0).get("password").toString());
        System.out.println(password);
        if (!password) {

            throw new BadRequestException("Wrong Credentials \r\n Invalid email or password");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getUsername(),
                authenticationReq.getPassword()));
        UserDetails details = customUserDetailsService.loadUserByUsername(authenticationReq.getUsername());
        System.out.println(details.getUsername() + " " + details.getPassword());

        String token = jwtUtil.generateToken(details);
        System.out.println(token);
        Map<String, Object> _map = new HashMap<>();
        _map.put("token", token);
        return _map;

    }

}
