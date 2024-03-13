package com.example.vk.internship.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_POSTS,
    ROLE_ALBUMS,
    ROLE_USERS,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}