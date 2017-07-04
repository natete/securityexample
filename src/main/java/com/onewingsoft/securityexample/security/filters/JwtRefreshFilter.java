package com.onewingsoft.securityexample.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onewingsoft.securityexample.security.jwt.extractor.TokenExtractor;
import com.onewingsoft.securityexample.security.model.JwtAuthenticationToken;
import com.onewingsoft.securityexample.security.model.JwtRawAccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 * @author igonzalez
 * @since 04/07/17.
 */
public class JwtRefreshFilter extends JwtLoginFilter {

    private final TokenExtractor tokenExtractor;
    private final ObjectMapper mapper;

    public JwtRefreshFilter(String url, AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler, TokenExtractor tokenExtractor) {
        super(url, successHandler, failureHandler);
        this.tokenExtractor = tokenExtractor;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String refreshToken = this.mapper.readValue(request.getInputStream(), String.class);

        JwtRawAccessToken rawAccessToken = new JwtRawAccessToken(refreshToken);

        // Retrieve User to build the user context and create the new token
        //        UserContext userContext = UserContext.create("admin", Collections.singletonList(CustomAuthority.ADMIN));

        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(rawAccessToken));
    }

}
