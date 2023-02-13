package com.hms.hospitalmanagementsystem.Entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class UserRole implements GrantedAuthority {
    private Long id;
    private String authority;
    private String username;

    @Override
    public String getAuthority() {
    
        return authority;
    }
    
    
}
