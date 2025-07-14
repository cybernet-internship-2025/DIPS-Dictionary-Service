package az.cybernet.internship.dictionary.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Development Server")
                ))
                .info(new Info()
                        .title("DIPS Dictionary Service API")
                        .version("1.0.0")
                        .description("""
                                This service provides RESTful APIs for managing dictionary categories and entries.
                                \n
                                **Available API Groups:**
                                - `/api/v1/categories` – CRUD operations for dictionary categories
                                - `/api/v1/dictionaries` – CRUD and restore operations for dictionary entries
                                """)
                );
    }
}
