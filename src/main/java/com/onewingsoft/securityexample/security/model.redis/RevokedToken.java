package com.onewingsoft.securityexample.security.model.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author igonzalez
 * @since 04/07/17.
 */
@RedisHash("revoked_tokens")
public class RevokedToken {

    @Id
    String key;

    protected RevokedToken() {
        super();
    }

    public RevokedToken(String key) {
        this.key = key;
    }

    /**
     * Sets new key.
     *
     * @param key New value of key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets key.
     *
     * @return Value of key.
     */
    public String getKey() {
        return key;
    }
}
