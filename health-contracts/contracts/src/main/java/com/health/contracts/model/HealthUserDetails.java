/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.model;

import com.health.contracts.entity.HealthUser;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Bobby
 */
@NoArgsConstructor
@Setter
@Getter
public class HealthUserDetails implements UserDetails{
    private String password;
    private String userName;
    private HealthUser user;
    
    public HealthUserDetails(HealthUser user){
        this.user = user;
    }

    @Override
    public String getUsername() {
        return this.userName;    
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
     }

    @Override
    public String getPassword() {
        return this.password;
    }
}
