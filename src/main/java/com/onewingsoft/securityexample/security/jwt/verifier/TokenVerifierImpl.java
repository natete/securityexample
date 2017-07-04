package com.onewingsoft.securityexample.security.jwt.verifier;

import com.onewingsoft.securityexample.security.jwt.verifier.redis.RevokedTokenRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
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

    @Override
    public boolean isRevoked(Claims claims) {
        return repository.existsToken(buildKey(claims));
    }

    @Override
    public void revokeToken(Claims claims) {
        repository.addRevokedToken(buildKey(claims), claims.getExpiration());
    }

    private String buildKey(Claims claims) {
        String jti = claims.getId();
        String aud = claims.getAudience();
        String iat = String.valueOf(claims.getIssuedAt().getTime());

        return jti + "-" + aud + "-" + iat;
    }
}
