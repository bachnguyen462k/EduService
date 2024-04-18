package com.javatechie.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "abc"
        ),
        description = "OpenApi doc",
        title = "OpenApi ",
        version = "1.0",
        license = @License(
            name = "License name",
            url = "http://some-url.com"
        ),
        termsOfService = "termsOfService"
    ),
    servers ={ 
        @Server(
            description="Local ENV",
            url = "http://localhost:8081/"
        )
    },
        security = {
                @SecurityRequirement(
                        name = "BearerAuth")
        }
)
@SecurityScheme(
    name = "BearerAuth",
    description = "JWT auth description",
    scheme = "Bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
