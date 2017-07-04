package com.onewingsoft.securityexample.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public class JwtAccessToken implements JwtToken {

    private final String token;
    @JsonIgnore
    private final Claims claims;

    protected JwtAccessToken(String token, Claims claims) {
        this.token = token;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    public Claims getClaims() {
        return this.claims;
    }
}
