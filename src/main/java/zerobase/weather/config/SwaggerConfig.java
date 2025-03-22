package main.java.zerobase.weather.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("날씨 일기 API")
                        .description("날씨와 일기 CRUD 기능을 제공하는 API 문서")
                        .version("1.0.0"));
    }
}
