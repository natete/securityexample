package com.onewingsoft.securityexample.security.providers;

import com.onewingsoft.securityexample.commons.props.AppPropsValues;
import com.onewingsoft.securityexample.security.model.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
@Component
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AppPropsValues appPropsValues;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtRawAccessToken rawAccessToken = (JwtRawAccessToken) authentication.getCredentials();

        Jws<Claims> claims = rawAccessToken.parseClaims(appPropsValues.getJwtSecret());

        // TODO check if the token has been revoked

        String subject = claims.getBody().getSubject();
        List<String> roles = claims.getBody().get(JwtTokenCreatorImpl.ROLES_KEY, List.class);
        List<CustomAuthority> authorities = roles.stream().map(CustomAuthority::valueOf).collect(Collectors.toList());

        UserContext context = UserContext.create(subject, authorities);

        return new JwtAuthenticationToken(context, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
