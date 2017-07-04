package com.onewingsoft.securityexample.security.jwt.verifier;

import io.jsonwebtoken.Claims;

/**
 *
 *
 * @author igonzalez
 * @since 04/07/17.
 */
public interface TokenVerifier {
    boolean isRevoked(Claims claims);

    void revokeToken(Claims claims);
}
