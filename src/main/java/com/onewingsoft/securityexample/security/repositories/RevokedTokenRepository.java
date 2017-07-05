package com.onewingsoft.securityexample.security.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 *
 *
 * @author igonzalez
 * @since 04/07/17.
 */
@Repository
public class RevokedTokenRepository {

    private static final String KEY = "revoked_tokens";

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RevokedTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addRevokedToken(String revokedToken, Date expirationTime) {
        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(revokedToken);
        boundValueOperations.set("");
        boundValueOperations.expireAt(expirationTime);
    }

    public boolean existsToken(String revokedToken) {
        return redisTemplate.getConnectionFactory().getConnection().exists(revokedToken.getBytes());
    }
}
