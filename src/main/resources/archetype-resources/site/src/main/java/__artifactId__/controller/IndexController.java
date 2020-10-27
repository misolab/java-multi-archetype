#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import ${groupId}.core.exception.BadRequestException;
import ${package}.domain.dao.MemberDao;
import ${package}.domain.entity.Member;
import ${package}.domain.repository.MemberRepository;
import ${package}.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

    final MemberDao memberDao;
    final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/api")
    public ResponseEntity api(@RequestParam String userId, String name) {

        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required parameter");
        }

        Member member = new Member();
        member.setUserId(userId);
        member.setName(StringUtils.isEmpty(name) ? userId : name);

        memberRepository.save(member);

        List<Member> result = memberDao.allMembers();
        List<MemberDto> response = result.stream().map(MemberDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String index(Model model, String msg) {
        model.addAttribute("msg", msg);
        model.addAttribute("current", memberDao.getDate());

        return "index";
    }
}
