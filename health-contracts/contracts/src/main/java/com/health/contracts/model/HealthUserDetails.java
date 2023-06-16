/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.contracts.model;

import com.health.contracts.entity.HealthUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Bobby
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList();
        auth.add(new SimpleGrantedAuthority(user.getRole()));
        return auth;
     }

    @Override
    public String getPassword() {
        return this.password;
    }
}
