package com.nostra.nostramovieapps;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.nostra.nostramovieapps.share.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@AutoConfigurationPackage
@EnableJpaRepositories
@Configuration
@EnableSwagger2
public class NostraMovieAppsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NostraMovieAppsApplication.class, args);
    }

    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select().paths(postPaths())
                .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties1.class))
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties2.class))
                .additionalModels(resolver.resolve(ApplicationProperties.ApplicationProperties3.class));
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/nostramovie.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Nostra Movie API").description("Nostra Movie API reference for developers").build();
    }
}
