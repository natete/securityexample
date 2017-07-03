package com.onewingsoft.securityexample.security.model;

import com.onewingsoft.securityexample.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

public class JwtRawAccessToken implements JwtToken {
    private String token;

    public JwtRawAccessToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
            // TODO check if the token has been revoked
            return jws;
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
