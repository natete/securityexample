package com.onewingsoft.securityexample.security.model;

import com.onewingsoft.securityexample.security.props.SecurityPropsValues;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 *
 *
 * @author igonzalez
 * @since 02/07/17.
 */
public abstract class JwtTokenCreator {

    private static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    protected final SecurityPropsValues propsValues;

    public JwtTokenCreator(SecurityPropsValues propsValues) {
        this.propsValues = propsValues;
    }

    /**
     * Used to support multi-tenancy
     * @return the tenant
     */
    protected abstract String getAudience();

    public JwtAccessToken createAccessToken(UserContext user) {
        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("Username is mandatory");
        }

        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            throw new IllegalArgumentException("Authorities are mandatory");
        }

        Claims claims = this.buildAccessTokenClaims(user);

        String jwt = this.buildJwt(claims);

        return new JwtAccessToken(jwt, claims);
    }

    public JwtToken createRefreshToken(UserContext user) {

        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalArgumentException("Username is mandatory");
        }

        Claims claims = this.buildRefreshTokenClaims(user);

        String jwt = this.buildJwt(claims);

        return new JwtAccessToken(jwt, claims);

    }

    /**
     * Extend this to add the claims that your application needs in the access token.
     * @param user the user owner of the JWT to be issued
     * @return the claims required by your application.
     */
    protected abstract Claims buildAccessTokenClaims(UserContext user);

    /**
     * Extend this to add the claims that your application needs in the refresh token.
     * @param user the user owner of the JWT to be issued
     * @return the claims required by your application.
     */
    protected abstract Claims buildRefreshTokenClaims(UserContext user);

    Claims buildBasicClaims(UserContext user, LocalDateTime currentTime) {

        Claims claims = Jwts.claims();

        claims.setId(UUID.randomUUID().toString());
        claims.setSubject(user.getUsername());
        claims.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()));
        return claims;
    }

    private String buildJwt(Claims claims) {
        return Jwts.builder()
                   .setIssuer(propsValues.getAppName())
                   .setAudience(this.getAudience()) // To support multi-tenant
                   .setId(UUID.randomUUID().toString())
                   .setClaims(claims)
                   .signWith(SIGNATURE_ALGORITHM, propsValues.getJwtSecret())
                   .compact();
    }
}
