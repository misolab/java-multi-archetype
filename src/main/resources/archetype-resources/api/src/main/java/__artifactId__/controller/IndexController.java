#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import ${package}.web.config.WebSecurityConfig;

@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfig {

    String[] permitUrl = { "/", "/ajax" , "/api/user/ip", "/api/user/login", "/api/user/logout" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(permitUrl).permitAll()
                .anyRequest().authenticated();
    }
}
