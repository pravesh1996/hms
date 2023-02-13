package com.hms.hospitalmanagementsystem.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import lombok.Data;

@Data
public class UserLogin implements UserDetails {


    private Long id;
    private String username;
    private String mobileNo;
    private String password;


    @Transient
    private transient Collection<UserRole> authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authority;
    }
    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }
    @Override
    public boolean isEnabled() {

        return true;
    }

    
}
