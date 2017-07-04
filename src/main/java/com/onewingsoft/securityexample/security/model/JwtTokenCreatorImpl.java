package com.onewingsoft.securityexample.security.model;

import com.onewingsoft.securityexample.security.props.SecurityPropsValues;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenCreatorImpl extends JwtTokenCreator {

    public static final String ROLES_KEY = "roles";

    @Autowired
    public JwtTokenCreatorImpl(SecurityPropsValues propsValues) {
        super(propsValues);
    }

    @Override
    protected Claims buildAccessTokenClaims(UserContext user) {
        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = this.buildBasicClaims(user, currentTime);
        claims.setExpiration(Date.from(currentTime.plusMinutes(this.propsValues.getJwtTokenExpirationTime())
                                                  .atZone(ZoneId.systemDefault()).toInstant()));

        claims.put(ROLES_KEY, user.getAuthorities().stream().map(CustomAuthority::getAuthority)
                                  .collect(Collectors.toList()));

        return claims;
    }

    @Override
    protected Claims buildRefreshTokenClaims(UserContext user) {

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = this.buildBasicClaims(user, currentTime);
        claims.setExpiration(Date.from(currentTime.plusMinutes(this.propsValues.getJwtRefreshTokenExpirationTime())
                                                  .atZone(ZoneId.systemDefault()).toInstant()));

        claims.put(ROLES_KEY, Collections.singletonList(CustomAuthority.REFRESH.getAuthority()));

        return claims;
    }

    @Override
    protected String getAudience() {
        return "";
    }
}
