package com.onewingsoft.securityexample.security.providers;

import com.onewingsoft.securityexample.security.model.CustomAuthority;
import com.onewingsoft.securityexample.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtLoginAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder encoder;
    // Add here as many dependencies as you might need to implement your security such as a jpa repository

    @Autowired
    public JwtLoginAuthenticationProvider(final BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null){
            throw new BadCredentialsException("No auth data provided");
        }

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        // Get the user you need to check if the credentials are valid
        final String validUsername = "admin";
        final String validPassword = encoder.encode("admin");

        if (!username.equals(validUsername) || !encoder.matches(password, validPassword)) {
            throw new BadCredentialsException("Usuario o contraseña incorrectas");
        }

        UserContext userContext = UserContext.create(validUsername, Collections.singletonList(CustomAuthority.ADMIN));

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
