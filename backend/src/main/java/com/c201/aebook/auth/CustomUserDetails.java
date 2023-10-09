package com.c201.aebook.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.c201.aebook.api.user.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private UserEntity user;
    List<GrantedAuthority> roles = new ArrayList<>();

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    public void setAuthorities(List<GrantedAuthority> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return String.valueOf(this.user.getId());
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.user.getId());
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
        return false;
    }
}
