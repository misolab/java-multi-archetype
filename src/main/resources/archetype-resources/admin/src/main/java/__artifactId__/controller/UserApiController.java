#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import static net.joins.common.util.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import ${package}.common.util.StringUtils;
import ${package}.web.exception.ForbiddenException;
import ${package}.web.vo.ApiResponse;

@RequestMapping("/api/user")
@RestController
public class UserApiController {

    @Getter
    @Setter
    static class LoginParam {
        @NotEmpty
        String username;

        @NotEmpty
        String password;

        boolean isAdmin() {
            if (StringUtils.isEmpty(username)) {
                return false;
            }
            return username.contains("admin");
        }

        String getToken(List<String> roles) {
            return String.format("%s|%s", username, String.join(",", roles));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginParam param) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        if (param.isAdmin()) {
            roles.add("ADMIN");
        }
        String token = param.getToken(roles);

        Map<String, Object> result = new HashMap<>();
        result.put("name", param.getUsername());
        result.put("roles", roles);
        result.put("token", token);

        ApiResponse response = ApiResponse.of(result);
        return response.toResponseEntity();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("X-Token") String xToken) {
        if (isNotEmpty(xToken)) {
            // remove token in member table
        }

        ApiResponse response = ApiResponse.of("result", true);
        return response.toResponseEntity();
    }

    @GetMapping("/info")
    public ResponseEntity<Object> info(@RequestHeader("X-Token") String xToken) {
        if (isNotEmpty(xToken)) {
            throw new ForbiddenException("Permission error");
        }

        String[] values = xToken.split("\\|");
        String name = values[0];
        String[] roles = values[1].split(",");

        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("token", xToken);
        result.put("roles", Arrays.asList(roles));

        ApiResponse response = ApiResponse.of(result);
        return response.toResponseEntity();
    }

}

