package com.onewingsoft.securityexample.security.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
public class UserContext {

    private final String username;
    private final Collection<CustomAuthority> authorities;

    private UserContext(String username, Collection<CustomAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static UserContext create(String username, Collection<CustomAuthority> authorities) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username is blank: " + username);
        }

        return new UserContext(username, authorities);
    }

    public String getUsername() {
        return username;
    }

    public Collection<CustomAuthority> getAuthorities() {
        return authorities;
    }
}
