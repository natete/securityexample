package com.onewingsoft.securityexample.security.jwt.extractor;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
public interface TokenExtractor {
    String extract(String payload);
}
