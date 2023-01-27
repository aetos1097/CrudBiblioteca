package com.example.obrestdatajpa.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
 */


import java.lang.annotation.Annotation;
import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacioon de la API REST
 * http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig {
    //esta anotacion indica un bean que estara disponible en el contenedor de beans
    /*
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiDetails(){

        return new ApiInfo("Spring Boot Books API REST",
                "Api rest docs ",
                "1.0" ,
                "http://google.com",
                new Contact("Juan","http://google.com","anpch@example.com") ,
                "MIT",
                "http://google.com",
                Collections.emptyList());
    }
     */
    @Bean
    public OpenAPI springBooksOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API REST BOOKS")
                        .description("trying that swagger ui works")
                        .version("1.0")
                        .license(new License().name("License").url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                        .description("Swagger documentation")
                        .url("aun no"));

    }







}
