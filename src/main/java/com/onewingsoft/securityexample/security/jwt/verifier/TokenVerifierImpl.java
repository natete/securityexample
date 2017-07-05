package com.onewingsoft.securityexample.security.jwt.verifier;

import com.onewingsoft.securityexample.security.repositories.RevokedTokenRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {@see TokenVerifier}
 *
 * @author igonzalez
 * @since 04/07/17.
 */
@Component
public class TokenVerifierImpl implements TokenVerifier {

    private final RevokedTokenRepository repository;

    @Autowired
    public TokenVerifierImpl(RevokedTokenRepository repository) {
        this.repository = repository;
    }

    /**
     * {@see TokenVerivier#isRevoked}
     */
    @Override
    public boolean isRevoked(Claims claims) {
        return repository.existsToken(buildKey(claims));
    }

    /**
     * {@see TokenVerivier#repositories}
     */
    @Override
    public void revokeToken(Claims claims) {
        repository.addRevokedToken(buildKey(claims), claims.getExpiration());
    }

    /**
     * Builds the key from the given claims.
     *
     * @param claims the claims of the token to extract the key from.
     * @return the built key.
     */
    private String buildKey(Claims claims) {
        String jti = claims.getId();
        String aud = claims.getAudience();
        String iat = String.valueOf(claims.getIssuedAt().getTime());

        return jti + "-" + aud + "-" + iat;
    }
}
