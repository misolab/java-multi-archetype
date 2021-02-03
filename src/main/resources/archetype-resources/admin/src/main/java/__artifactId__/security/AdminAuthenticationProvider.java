#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.security;

import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ${package}.common.jwt.JwtUtil;
import ${package}.common.util.LDAPAuthenticator;
import ${package}.common.util.LDAPAuthenticator.Result;
import ${package}.common.util.ListUtils;
import ${package}.domain.entity.Member;
import ${package}.domain.service.MemberService;
import ${package}.web.dto.UserInfo;
import ${package}.web.security.LoginAuthentication;
import ${package}.web.security.UserInfoAuthentication;
import ${package}.web.security.WebAuthenticationProvider;

@RequiredArgsConstructor
@Component
public class AdminAuthenticationProvider implements WebAuthenticationProvider {

    final JwtUtil jwtUtil;
    final LDAPAuthenticator ldap;
    final MemberService memberService;

    static final List<GrantedAuthority> grantedAuthority(String roles) {
        List<String> roleList = ListUtils.asList(roles);
        return roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    UserInfoAuthentication makeAuthentication(UserInfo userInfo, String token, String roles) {
        List<GrantedAuthority> authority = grantedAuthority(roles);
        UserInfoAuthentication authentication = new UserInfoAuthentication(userInfo, token, authority);
        return authentication;
    }

    @Override
    public Authentication validateToken(Authentication authentication) {
        LoginAuthentication in = (LoginAuthentication) authentication;
        String token = in.getToken();
        String ipAddress = in.getIpAddress();
        String username = "";

        try {
            DecodedJWT decoded = jwtUtil.decode(token);
            username = decoded.getSubject();
        } catch (Exception e) {
            throw new DisabledException("invalid token");
        }

        // is member?
        Member member = memberService.findMember(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + "is not member");
        }
        // token
        if (!member.matchToken(token)) {
            throw new DisabledException(username + " is invalid token");
        }
        // ip
        if (!member.enableIp(ipAddress)) {
            throw new DisabledException(username + " is other ip");
        }

        String roles = member.getRoles();
        UserInfo userInfo = UserInfo.builder().username(username).token(token).roles(roles).build();

        return makeAuthentication(userInfo, token, roles);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthentication in = (LoginAuthentication) authentication;
        String username = in.getUsername();
        String password = in.getPassword();
        String ipAddress = in.getIpAddress();

        // is member?
        Member member = memberService.findMember(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + " is not member");
        }
        // error-count
        if (!member.enableLogin()) {
            throw new LockedException(username + " is error count over");
        }
        // ip
        if (!member.enableIp(ipAddress)) {
            throw new DisabledException(username + " is other ip");
        }
        // ldap
        Result result = ldap.login(username, password);
        if (!result.isOk()) {
            member.addError();
            memberService.update(member);

            throw new BadCredentialsException(result.getMessage());
        }

        String roles = member.getRoles();
        String token = jwtUtil.encode(username, "roles", roles);
        UserInfo userInfo = UserInfo.builder().username(username).token(token).roles(roles).build();

        // update token
        member.reset(token);
        memberService.update(member);

        return makeAuthentication(userInfo, token, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        if (authentication == LoginAuthentication.class) {
            return true;
        }
        return false;
    }

}

