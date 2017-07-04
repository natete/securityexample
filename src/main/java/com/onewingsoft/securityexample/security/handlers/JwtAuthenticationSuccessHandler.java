package com.onewingsoft.securityexample.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.securityexample.security.model.JwtToken;
import com.onewingsoft.securityexample.security.model.JwtTokenCreator;
import com.onewingsoft.securityexample.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    private final ObjectMapper mapper;
    private final JwtTokenCreator tokenCreator;

    @Autowired
    public JwtAuthenticationSuccessHandler(JwtTokenCreator tokenCreator) {
        this.mapper = new ObjectMapper();
        this.tokenCreator = tokenCreator;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        UserContext userContext = (UserContext) auth.getPrincipal();

        JwtToken accessToken = tokenCreator.createAccessToken(userContext);
        JwtToken refreshToken = tokenCreator.createRefreshToken(userContext);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put(ACCESS_TOKEN, accessToken.getToken());
        tokenMap.put(REFRESH_TOKEN, refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
