#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    String username;
    String token;
    String roles;

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("token", token);
        result.put("roles", roles);
        return result;
    }
}
