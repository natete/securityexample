package com.onewingsoft.securityexample.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtInvalidToken extends AuthenticationException {

    public JwtInvalidToken(String msg) {
        super(msg);
    }
}
