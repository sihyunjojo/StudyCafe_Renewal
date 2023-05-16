package studycafe.studycaferenewal.service;

import studycafe.studycaferenewal.domain.Member;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface MemberService {

    public String join(Member member);
    public Optional<Member> findById(Member member);
    public Optional<Member> findByUserId(Member member);
    public Optional<Member> findMemberByNameAndPhone(Member member);
    public void update(Member updateMember);

}

