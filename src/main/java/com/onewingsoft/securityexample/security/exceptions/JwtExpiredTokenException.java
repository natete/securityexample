package com.onewingsoft.securityexample.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtExpiredTokenException extends AuthenticationException {

    public JwtExpiredTokenException(String msg, Throwable t) {
        super(msg, t);
    }
}
