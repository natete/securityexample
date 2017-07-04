package com.onewingsoft.securityexample.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private JwtRawAccessToken rawAccessToken;
    private UserContext userContext;

    public JwtAuthenticationToken(JwtRawAccessToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.userContext = null;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = user;
        this.rawAccessToken = null;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessToken = null;
        this.userContext = null;
    }
}
