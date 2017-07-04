package com.onewingsoft.securityexample.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author igonzalez
 * @since 01/06/17.
 */
public class LogoutRequest {

    private final String accessToken;
    private final String refreshToken;

    @JsonCreator
    public LogoutRequest(@JsonProperty("accessToken") String accessToken,
            @JsonProperty("refreshToken") String refreshToken) {
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
