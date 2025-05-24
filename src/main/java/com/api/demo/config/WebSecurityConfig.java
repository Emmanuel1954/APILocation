package com.api.demo.config;

import com.api.demo.config.model.Constans; // Asegúrate de que esta clase exista y contenga LOGIN_URL
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
class WebSecurityConfig {

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // Deshabilita CSRF para APIs REST
                .authorizeHttpRequests(authz -> authz
                        // Permite el acceso sin autenticación a la URL de login
                        .requestMatchers(Constans.LOGIN_URL).permitAll()
                        // Permite las peticiones OPTIONS (pre-flight requests de CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // ¡PERMITIR ACCESO A LAS RUTAS DE SWAGGER UI Y OPENAPI DOCS!
                        .requestMatchers(
                                "/v3/api-docs/**",       // Para el JSON de la especificación OpenAPI
                                "/swagger-ui/**",       // Para los archivos estáticos de Swagger UI
                                "/swagger-ui.html",     // La página principal de Swagger UI
                                "/webjars/**"           // Para los webjars de Swagger UI
                        ).permitAll()
                        // Todas las demás solicitudes necesitan ser autenticadas
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}