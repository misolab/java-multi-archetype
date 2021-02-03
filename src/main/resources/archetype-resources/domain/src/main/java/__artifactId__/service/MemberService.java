#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.Member;
import net.joins.domain.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {

    final MemberRepository memberRepository;

    public Member findMember(String username) {
        return memberRepository.findByUsername(username);
    }

    public void update(Member member) {
        memberRepository.save(member);
    }

    public void resetToken(String token) {
        memberRepository.findByToken(token).ifPresent(member -> {
            member.reset(null);
            memberRepository.save(member);
        });
    }

}
