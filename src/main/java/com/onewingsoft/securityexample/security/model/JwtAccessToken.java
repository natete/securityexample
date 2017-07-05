package com.onewingsoft.securityexample.security.model;

/**
 * Represents an access token.
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public class JwtAccessToken implements JwtToken {

    private final String token;

    /**
     * Default constructor.
     * @param token
     */
    protected JwtAccessToken(String token) {
        this.token = token;
    }

    /**
     * @see JwtToken#getToken()
     * @return a String representing the token.
     */
    @Override
    public String getToken() {
        return this.token;
    }
}
