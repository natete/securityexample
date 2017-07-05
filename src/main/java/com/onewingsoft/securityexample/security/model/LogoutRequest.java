package com.onewingsoft.securityexample.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onewingsoft.securityexample.security.handlers.JwtAuthenticationSuccessHandler;

/**
 * @author igonzalez
 * @since 01/06/17.
 */
public class LogoutRequest {

    private final String accessToken;
    private final String refreshToken;

    @JsonCreator
    public LogoutRequest(@JsonProperty(JwtAuthenticationSuccessHandler.ACCESS_TOKEN) String accessToken,
            @JsonProperty(JwtAuthenticationSuccessHandler.REFRESH_TOKEN) String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
