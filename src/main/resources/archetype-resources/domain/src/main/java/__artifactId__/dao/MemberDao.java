#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.dao;

import ${package}.${artifactId}.entity.Member;
import ${package}.${artifactId}.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {

    @Autowired
    SqlSession sqlSession;

    @Autowired
    MemberMapper memberMapper;

    public String getDate() {
        String result = memberMapper.getDate();
        return result;
    }

    public List<Member> allMembers() {
        List<Member> result = sqlSession.selectList("allMembers");
        return result;
    }


}
