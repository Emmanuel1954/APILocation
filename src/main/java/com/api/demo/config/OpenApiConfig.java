package com.api.demo.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License; // Esta importación no se usa, podrías quitarla
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "APILocation - API de Rastreo y Gestión de Personas",
                version = "1.0",
                description = "Proyecto poo (geocodificación con Google Maps) y gestión de usuarios. Proporciona acceso seguro vía REST API con autenticación JWT y API Key.",
                contact = @Contact(name = "CIPA Sebastian-Kevin-Emmanuel", url = "https://github.com/Emmanuel1954/APILocation") // ¡Aquí estaba la coma extra!
        ),
        servers = {
                @Server(url = "http://localhost:8088", description = "Servidor Local de Desarrollo"),

        }
)
// Define el esquema de seguridad para JWT (Bearer Token)
@SecurityScheme(
        name = "bearerAuth", // Este nombre se usará en los controladores
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Token JWT de autenticación. Inclúyelo con el prefijo 'Bearer '."
)
public class OpenApiConfig {

}