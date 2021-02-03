#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.entity;


import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.Assert;

import ${package}.common.Constants;
import ${package}.common.util.StringUtils;


@Table(name = "TB_MEMBER", indexes = {
        @Index(name = "IDX_USERNAME", columnList = "USERNAME")
})
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "USERNAME", nullable = false)
    String username;

    @Column(name = "X_TOKEN")
    String token;

    @Column(name = "ROLES")
    String roles;

    @Column(name = "ERROR_COUNT", columnDefinition = "integer default 0")
    Integer errorCount;

    @Column(name = "ALLOW_IP")
    String allowIp;

    public String getRoles() {
        return roles;
    }

    public boolean enableLogin() {
        return errorCount < Constants.MEMBER_ERROR_COUNT;
    }

    public boolean enableIp(String ipAddress) {
        return StringUtils.contains(allowIp, ipAddress);
    }

    public void reset(String token) {
        errorCount = 0;
        this.token = token;
    }

    public boolean matchToken(String value) {
        return StringUtils.equals(token, value);
    }

    public void addError() {
        errorCount += 1;
    }

}

