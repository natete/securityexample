package com.onewingsoft.securityexample.security.jwt.extractor;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public interface TokenExtractor {
    String extract(String payload);
}
