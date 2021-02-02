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
}

