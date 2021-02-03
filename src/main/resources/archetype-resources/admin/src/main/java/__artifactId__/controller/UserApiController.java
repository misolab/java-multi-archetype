#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ${package}.admin.security.AdminAuthenticationProvider;
import ${package}.domain.service.MemberService;
import ${package}.web.dto.UserInfo;
import ${package}.web.exception.ForbiddenException;
import ${package}.web.security.LoginAuthentication;
import ${package}.web.security.UserInfoAuthentication;
import ${package}.web.util.CurrentUser;
import ${package}.web.util.RequestUtil;
import ${package}.web.vo.ApiResponse;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {

    final AdminAuthenticationProvider authenticationProvider;
    final MemberService memberService;

    @Getter
    @Setter
    static class LoginParam {
        @NotEmpty
        String username;

        @NotEmpty
        String password;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginParam param, HttpServletRequest request) {
        ApiResponse response;
        String ipAddress = RequestUtil.getClientIP(request);
        LoginAuthentication in = new LoginAuthentication(param.getUsername(), param.getPassword(), null, ipAddress);
        try {
            UserInfoAuthentication out = (UserInfoAuthentication) authenticationProvider.authenticate(in);
            // UserInfo를 받은 AuthenticationToken을 저장하자
            SecurityContextHolder.getContext().setAuthentication(out);
            response = ApiResponse.of(out.getUserInfo());
        } catch (Exception e) {
            response = ApiResponse.error(new ForbiddenException(e.getLocalizedMessage()));
        }

        return response.toResponseEntity();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@CurrentUser UserInfo userInfo) {
        memberService.resetToken(userInfo.getToken());

        ApiResponse response = ApiResponse.of().add("result", true);
        return response.toResponseEntity();
    }

    @GetMapping("/info")
    public ResponseEntity<Object> info(@CurrentUser UserInfo userInfo) {
        log.info("userInfo {}", userInfo.getUsername());

        ApiResponse response = ApiResponse.of(userInfo.toMap());
        return response.toResponseEntity();
    }
}
