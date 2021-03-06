package com.example.springboottutorial.springboot100steps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api()
    {
        return new Docket((DocumentationType.SWAGGER_2)).apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/users/**"))
                .build();
    }

    //Swagger Meta data URL : http://localhost:8080/v2/api-docs
    //Swagger UI URL : http://localhost:8080/swagger-ui.html

    public ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("User Management Service")
                .description("This page lists all APIs of User Management")
                .version("2.0")
                .contact(new Contact("Garima", "https://www.garide.com", "garide@gmil.com"))
                .build();
    }
}
