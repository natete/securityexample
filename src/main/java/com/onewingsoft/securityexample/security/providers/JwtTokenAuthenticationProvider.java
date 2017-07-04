package com.onewingsoft.securityexample.security.providers;

import com.onewingsoft.securityexample.security.exceptions.JwtInvalidToken;
import com.onewingsoft.securityexample.security.jwt.verifier.TokenVerifier;
import com.onewingsoft.securityexample.security.model.*;
import com.onewingsoft.securityexample.security.props.SecurityPropsValues;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
@Component
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    private final SecurityPropsValues securityPropsValues;
    private final TokenVerifier tokenVerifier;

    @Autowired
    public JwtTokenAuthenticationProvider(SecurityPropsValues securityPropsValues, TokenVerifier tokenVerifier) {
        this.securityPropsValues = securityPropsValues;
        this.tokenVerifier = tokenVerifier;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtRawAccessToken rawAccessToken = (JwtRawAccessToken) authentication.getCredentials();

        Claims claims = rawAccessToken.parseClaims(securityPropsValues.getJwtSecret()).getBody();

        if (tokenVerifier.isRevoked(claims)) {
            throw new JwtInvalidToken("The token has been revoked");
        }

        String subject = claims.getSubject();
        List<String> roles = claims.get(JwtTokenCreatorImpl.ROLES_KEY, List.class);
        List<CustomAuthority> authorities = roles.stream().map(CustomAuthority::valueOf).collect(Collectors.toList());

        UserContext userContext = UserContext.create(subject, authorities);

        if (roles.contains(CustomAuthority.REFRESH.getAuthority())) {
            return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
        } else {
            return new JwtAuthenticationToken(userContext, authorities);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
