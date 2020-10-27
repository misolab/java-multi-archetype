#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "${groupId}")
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }
}
