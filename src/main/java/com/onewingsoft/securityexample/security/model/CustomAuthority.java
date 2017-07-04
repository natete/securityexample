package com.onewingsoft.securityexample.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public enum CustomAuthority implements GrantedAuthority {

    REFRESH("REFRESH"), ADMIN("ADMIN");

    private String authority;

    CustomAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
