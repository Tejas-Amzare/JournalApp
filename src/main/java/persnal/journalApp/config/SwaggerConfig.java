package persnal.journalApp.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Social Media API")
                .version("1.0")
                .description("Backend API for Social Media Application")
                .contact(new Contact()
                    .name("Tejas Gajanan Amzare")
                    .email("tejasamzare10@gmail.com")
                    .url("https://github.com/Tejas-Amzare")
                    ));
                    
    }
}
