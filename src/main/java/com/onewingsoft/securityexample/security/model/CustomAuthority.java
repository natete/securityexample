package com.onewingsoft.securityexample.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
public enum CustomAuthority implements GrantedAuthority {

    REFRESH("REFRESH");

    private String authority;

    CustomAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}