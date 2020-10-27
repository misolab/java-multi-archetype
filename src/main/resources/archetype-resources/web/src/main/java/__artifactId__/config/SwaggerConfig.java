#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.${artifactId}.plugins.Docket;

public interface SwaggerConfig {

    default ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Joins Multi Project")
                .description("API documents")
                .build();
    }

    @Bean
    public Docket apiDocket();
}
