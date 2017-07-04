package com.onewingsoft.securityexample.security.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Values of the keys in the property resource security.properties. Return the property value associated with the given key,
 * or null if the key cannot be resolved.
 *
 * @author igonzalez
 *
 */
@Component
@PropertySource(value = { "classpath:security.properties" })
public class SecurityPropsValues {

    /*
     * NOTE: Sort the private variables in alphabetical order
     */

    @Value("${" + SecurityPropsKeys.APP_NAME + "}")
    private String appName;

    @Value("${" + SecurityPropsKeys.JWT_REFRESH_TOKEN_EXPIRATION_TIME + "}")
    private int jwtRefreshTokenExpirationTime;

    @Value("${" + SecurityPropsKeys.JWT_SECRET + "}")
    private String jwtSecret;

    @Value("${" + SecurityPropsKeys.JWT_TOKEN_EXPIRATION_TIME + "}")
    private int jwtTokenExpirationTime;

    private String swaggerInfoVersion;

    public String getAppName() {
        return appName;
    }

    public int getJwtRefreshTokenExpirationTime() {
        return jwtRefreshTokenExpirationTime;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public int getJwtTokenExpirationTime() {
        return jwtTokenExpirationTime;
    }

    public String getSwaggerInfoVersion() {
        return swaggerInfoVersion;
    }

}
