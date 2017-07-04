package com.onewingsoft.securityexample.configuration;

import com.onewingsoft.securityexample.security.config.RedisConfig;
import com.onewingsoft.securityexample.security.config.WebSecurityConfig;
import org.springframework.context.annotation.*;

/**
 * Class that provides the root application context.
 *
 * @author iiglesias
 *
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySource(value = { "classpath:app.properties" })
@ComponentScan(value = { "com.onewingsoft.securityexample" })
@Import({ WebSecurityConfig.class, RedisConfig.class })
public class AppConfig {

}
