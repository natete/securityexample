package com.onewingsoft.securityexample.security.model;

import com.onewingsoft.securityexample.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public class JwtRawAccessToken implements JwtToken {
    private String token;

    public JwtRawAccessToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) throws AuthenticationException {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException(ex.getMessage(), ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException("Expired token", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
