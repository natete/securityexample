package com.onewingsoft.securityexample.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final JwtRawAccessToken rawAccessToken;
    private final UserContext userContext;

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
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
