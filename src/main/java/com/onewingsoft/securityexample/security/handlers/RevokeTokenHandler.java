package com.onewingsoft.securityexample.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.securityexample.security.exceptions.JwtExpiredTokenException;
import com.onewingsoft.securityexample.security.exceptions.JwtInvalidToken;
import com.onewingsoft.securityexample.security.jwt.verifier.TokenVerifier;
import com.onewingsoft.securityexample.security.model.JwtRawAccessToken;
import com.onewingsoft.securityexample.security.model.LogoutRequest;
import com.onewingsoft.securityexample.security.props.SecurityPropsValues;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 * @author igonzalez
 * @since 04/07/17.
 */
@Component
public class RevokeTokenHandler implements LogoutHandler {

    private final SecurityPropsValues securityPropsValues;
    private final ObjectMapper mapper;
    private final TokenVerifier tokenVerifier;

    @Autowired
    public RevokeTokenHandler(SecurityPropsValues securityPropsValues, TokenVerifier tokenVerifier) {
        this.securityPropsValues = securityPropsValues;
        this.tokenVerifier = tokenVerifier;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws AuthenticationException {

        try {
            final LogoutRequest logoutRequest = mapper.readValue(request.getInputStream(), LogoutRequest.class);

            JwtRawAccessToken accessToken = new JwtRawAccessToken(logoutRequest.getAccessToken());
            JwtRawAccessToken refreshToken = new JwtRawAccessToken(logoutRequest.getRefreshToken());

            handleRevokedToken(accessToken);
            handleRevokedToken(refreshToken);

        } catch (IOException e) {
            throw new JwtInvalidToken("Invalid payload");
        }
    }

    private void handleRevokedToken(JwtRawAccessToken token) throws AuthenticationException {
        try {
            Claims claims = token.parseClaims(securityPropsValues.getJwtSecret()).getBody();

            tokenVerifier.revokeToken(claims);

        } catch (JwtExpiredTokenException ex) {
            // If the token is expired we don't need to blacklist it, so we catch this exception.
        }
    }
}