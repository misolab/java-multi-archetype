#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import ${package}.${artifactId}.dto.UserInfo;

public class UserInfoAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    UserInfo userInfo;
    String token;

    public UserInfoAuthentication(UserInfo principal, String credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        userInfo = principal;
        token = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userInfo;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.token = null;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo.toMap();
    }

}
