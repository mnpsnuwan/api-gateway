package com.imatrix.gateway.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by IntelliJ IDEA.
 * Project : APIGateway
 * Class : {@link SecurityConfig}
 *
 * @author : Nuwan Samarasinghe
 */

@Configuration
public class SecurityConfig
{

    //Without any authentication
    private final String[] freeResourceUrls = { "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
            "/swagger-resources/**", "/api-docs/**", "/aggregate/**" };

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity ) throws Exception
    {
        return httpSecurity.authorizeHttpRequests( authorize -> authorize
                                                                        .requestMatchers( freeResourceUrls )
                                                                        .permitAll()
                                                                        .anyRequest().authenticated() )
                           .oauth2ResourceServer( oauth2 -> oauth2.jwt( Customizer.withDefaults() ) )
                           .build();
    }
}
