#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public interface WebAuthenticationProvider extends AuthenticationProvider {

    public Authentication validateToken(Authentication authentication);

}
