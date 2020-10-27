#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.dto;

import ${package}.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberDto {
    String name;
    String mail;

    public MemberDto(Member member) {
        if (member.getName() != null) {
            this.name = member.getName().toUpperCase();
            this.mail = member.getUserId() + "@joins.com";
        }
    }
}
