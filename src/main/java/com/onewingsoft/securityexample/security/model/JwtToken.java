package com.onewingsoft.securityexample.security.model;

/**
 * Represents a JWT token.
 */
public interface JwtToken {

    /**
     * Returns the value of the token.
     *
     * @return the token.
     */
    String getToken();
}
