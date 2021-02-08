#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import ${package}.webapp.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigImpl implements SwaggerConfig {
    @Override
    public Docket ${artifactId}Docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("${artifactId}")
                .${artifactId}Info(${artifactId}Info())
                .select()
                .${artifactId}s(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/${artifactId}/**"))
                .build();
    }
}
