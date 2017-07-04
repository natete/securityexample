package com.onewingsoft.securityexample.security.jwt.verifier.redis;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 *
 *
 * @author igonzalez
 * @since 04/07/17.
 */
@RedisHash("revoked_tokens")
@Data
@NoArgsConstructor
public class RevokedToken {

    @Id
    String key;
}
