package com.onewingsoft.securityexample.security.filters;

import com.onewingsoft.securityexample.security.config.WebSecurityConfig;
import com.onewingsoft.securityexample.security.jwt.extractor.TokenExtractor;
import com.onewingsoft.securityexample.security.model.JwtAuthenticationToken;
import com.onewingsoft.securityexample.security.model.JwtRawAccessToken;
import com.onewingsoft.securityexample.security.model.JwtTokenCreator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author natete
 * @since 02/07/17.
 */
public class JwtTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    public JwtTokenFilter(AuthenticationFailureHandler failureHandler, TokenExtractor tokenExtractor,
            RequestMatcher requestMatcher) {
        super(requestMatcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String tokenPayload = request.getHeader(WebSecurityConfig.TOKEN_HEADER);

        JwtRawAccessToken rawAccessToken = new JwtRawAccessToken(tokenExtractor.extract(tokenPayload));

        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(rawAccessToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
