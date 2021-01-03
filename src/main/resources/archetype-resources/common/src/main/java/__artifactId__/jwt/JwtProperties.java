#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.jwt;

import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public interface JwtProperties {
    Algorithm algorithm();

    Date expirationTime();
}
