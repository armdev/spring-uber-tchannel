package io.project.app.client.tchannel;

import org.springdoc.core.models.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

    @Bean
    @Lazy
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("client")
                .packagesToScan("io.project")
                .pathsToMatch("/api/**")
                .build();
    }

}
