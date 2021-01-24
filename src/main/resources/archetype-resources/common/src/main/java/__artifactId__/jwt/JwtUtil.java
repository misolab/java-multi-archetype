#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import ${package}.${artifactId}.util.CheckUtils;
import ${package}.${artifactId}.util.StringUtils;

import java.util.Date;
import java.util.Map;

public class JwtUtil implements JwtProperties {

    final String SECRET;
    final long LIMIT;

    public JwtUtil(String secret, long limit) {
        SECRET = secret;
        this.LIMIT = limit;
    }

    @Override
    public Algorithm algorithm() {
        return Algorithm.HMAC512(SECRET.getBytes());
    }

    @Override
    public Date expirationTime() {
        return new Date(System.currentTimeMillis() + LIMIT);
    }

    public DecodedJWT decode(String token) {
        return JWT.require(algorithm()).build().verify(token);
    }

    public String encode(String subject, Map<String, String> claim) {
        JWTCreator.Builder jwt = JWT.create();
        if (StringUtils.isNotEmpty(subject)) {
            jwt.withSubject(subject);
        }
        if (CheckUtils.isNotEmpty(claim)) {
            for (String name : claim.keySet()) {
                String value = claim.get(name);
                jwt.withClaim(name, value);
            }
        }
        String token = jwt.withExpiresAt(expirationTime())
                .sign(algorithm());
        return token;
    }

    public String encode(String subject, String name, String value) {
        String token = JWT.create().withSubject(subject)
                .withClaim(name, value)
                .withExpiresAt(expirationTime())
                .sign(algorithm());
        return token;
    }
}
