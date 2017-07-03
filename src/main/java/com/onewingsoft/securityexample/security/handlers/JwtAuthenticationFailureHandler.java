package com.onewingsoft.securityexample.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.securityexample.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final String BAD_CREDENTIALS_CODE = "13";
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {

        ErrorResponse errorResponse;

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Manage different exception types such as token expired, bad credentials etc.
        if (e instanceof BadCredentialsException) {
            errorResponse = new ErrorResponse(BAD_CREDENTIALS_CODE, "Bad Credentials");
        } else {
            errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), "Unauthorized");
        }

        mapper.writeValue(httpServletResponse.getWriter(), errorResponse);
    }
}
