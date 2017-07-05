package com.onewingsoft.securityexample.configuration;

import com.onewingsoft.securityexample.security.config.RedisConfig;
import com.onewingsoft.securityexample.security.config.WebSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that provides the root application context.
 *
 * @author iiglesias
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySource(value = { "classpath:app.properties" })
@ComponentScan(value = { "com.onewingsoft.securityexample" })
@Import({ WebSecurityConfig.class, RedisConfig.class })
public class AppConfig {

}
