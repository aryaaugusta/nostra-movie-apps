package com.nostra.nostramovieapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@AutoConfigurationPackage
@EnableJpaRepositories
public class NostraMovieAppsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NostraMovieAppsApplication.class, args);
    }

}
