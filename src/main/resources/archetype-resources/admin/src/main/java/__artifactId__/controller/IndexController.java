#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import ${package}.domain.entity.Member;
import ${package}.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/login"})
    public String entry() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/ajax")
    public String api(String msg) {
        Member member = new Member();
        member.setName(msg);
        member.setUserId(msg);

        MemberDto memberDto = new MemberDto(member);
        return "This is ${parentArtifactId} Site " + memberDto.toString();
    }


}
