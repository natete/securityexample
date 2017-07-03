package com.onewingsoft.securityexample.security.filters;

import com.onewingsoft.securityexample.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 *
 *
 * @author natete
 * @since 02/07/17.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE "));
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        source.registerCorsConfiguration(WebSecurityConfig.API_ENDPOINTS, config);

        return new CorsFilter(source);
    }
}