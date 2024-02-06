package com.nostra.nostramovieapps.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.nostra.nostramovieapps.share.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    /*@Autowired
    private TypeResolver resolver;

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/nostramovie.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Nostra Movie API").description("Nostra Movie API reference for developers").build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select().paths(postPaths())
                .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties1.class))
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties2.class))
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties3.class))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nostra.nostramovieapps"))
                .paths(PathSelectors.any())
                .build();
    }*/
}
