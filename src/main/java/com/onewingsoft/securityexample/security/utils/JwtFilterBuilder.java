package com.onewingsoft.securityexample.security.utils;

import com.onewingsoft.securityexample.security.config.WebSecurityConfig;
import com.onewingsoft.securityexample.security.filters.JwtLoginFilter;
import com.onewingsoft.securityexample.security.filters.JwtRefreshFilter;
import com.onewingsoft.securityexample.security.filters.JwtTokenFilter;
import com.onewingsoft.securityexample.security.jwt.extractor.TokenExtractor;
import com.onewingsoft.securityexample.security.matchers.SkipPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
@Component
public class JwtFilterBuilder {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtFilterBuilder(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,
            TokenExtractor tokenExtractor, AuthenticationManager authenticationManager) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
        this.authenticationManager = authenticationManager;
    }

    public JwtTokenFilter buildJwtTokenFilter() throws Exception {

        List<String> pathsToSkip = Arrays
                .asList(WebSecurityConfig.LOGIN_ENDPOINT, WebSecurityConfig.REFRESH_TOKEN_ENDPOINT);

        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, WebSecurityConfig.API_ENDPOINTS);

        JwtTokenFilter filter = new JwtTokenFilter(failureHandler, tokenExtractor, matcher);

        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }

    public JwtLoginFilter buildJwtLoginFilter() throws Exception {
        JwtLoginFilter filter = new JwtLoginFilter(WebSecurityConfig.LOGIN_ENDPOINT, successHandler, failureHandler);

        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }

    public JwtRefreshFilter buildJwtRefreshFilter() {
        JwtRefreshFilter filter = new JwtRefreshFilter(WebSecurityConfig.REFRESH_TOKEN_ENDPOINT, successHandler, failureHandler, tokenExtractor);

        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }
}
