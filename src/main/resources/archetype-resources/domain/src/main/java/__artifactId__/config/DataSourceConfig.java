#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.${artifactId}.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Getter @Setter
@Configuration
@PropertySources({
        @PropertySource("classpath:/datasource.properties"),
        @PropertySource(value = "classpath:/datasource-${symbol_dollar}{spring.profiles.active}.properties", ignoreResourceNotFound = true)
})
@MapperScan(basePackages = {"${package}.${artifactId}.mapper"})
@EnableJpaRepositories(basePackages = {"${groupId}"})
@EntityScan(basePackages = {"${package}.${artifactId}.entity"})
public class DataSourceConfig {
}
