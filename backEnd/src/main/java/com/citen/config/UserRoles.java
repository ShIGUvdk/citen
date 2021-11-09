package com.citen.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum UserRoles {
    USER(Set.of(UserPersmission.USER_READ)), 
    ADMIN(Set.of(UserPersmission.USER_READ, UserPersmission.USER_WRITE));
    
    private final Set<UserPersmission> permissions;

    UserRoles(Set<UserPersmission> permissions){
        this.permissions = permissions;
    }
    
    public Set<UserPersmission> getPermission(){
        return permissions;
    }
    
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermission().stream()
        .map(UserPersmission -> new SimpleGrantedAuthority(UserPersmission.getPermission()))
        .collect(Collectors.toSet());
    }

    
}
