package com.onewingsoft.securityexample.commons.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Values of the keys in the property resource app.properties. Return the property value associated with the given key,
 * or null if the key cannot be resolved.
 *
 * @author iiglesias
 *
 */
@Component
public class AppPropsValues {

    /*
     * NOTE: Sort the private variables in alphabetical order
     */

    @Value("${" + AppPropsKeys.APP_NAME + "}")
    private String appName;

    @Value("${" + AppPropsKeys.JWT_REFRESH_TOKEN_EXPIRATION_TIME + "}")
    private int jwtRefreshTokenExpirationTime;

    @Value("${" + AppPropsKeys.JWT_SECRET + "}")
    private String jwtSecret;

    @Value("${" + AppPropsKeys.JWT_TOKEN_EXPIRATION_TIME + "}")
    private int jwtTokenExpirationTime;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_CONTACT_EMAIL + "}")
    private String swaggerInfoContactEmail;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_CONTACT_NAME + "}")
    private String swaggerInfoContactName;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_CONTACT_URL + "}")
    private String swaggerInfoContactUrl;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_DESCRIPTION + "}")
    private String swaggerInfoDescription;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_LICENSE + "}")
    private String swaggerInfoLicense;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_LICENSE_URL + "}")
    private String swaggerInfoLicenseURL;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_TERMS_OF_SERVICE_URL + "}")
    private String swaggerInfoTermsOfServiceUrl;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_TITLE + "}")
    private String swaggerInfoTitle;

    @Value("${" + AppPropsKeys.SWAGGER_INFO_VERSION + "}")
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

    public String getSwaggerInfoContactEmail() {
        return swaggerInfoContactEmail;
    }

    public String getSwaggerInfoContactName() {
        return swaggerInfoContactName;
    }

    public String getSwaggerInfoContactUrl() {
        return swaggerInfoContactUrl;
    }

    public String getSwaggerInfoDescription() {
        return swaggerInfoDescription;
    }

    public String getSwaggerInfoLicense() {
        return swaggerInfoLicense;
    }

    public String getSwaggerInfoLicenseURL() {
        return swaggerInfoLicenseURL;
    }

    public String getSwaggerInfoTermsOfServiceUrl() {
        return swaggerInfoTermsOfServiceUrl;
    }

    public String getSwaggerInfoTitle() {
        return swaggerInfoTitle;
    }

    public String getSwaggerInfoVersion() {
        return swaggerInfoVersion;
    }

}
