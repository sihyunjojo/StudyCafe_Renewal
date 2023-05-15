package studycafe.studycaferenewal.service;

import studycafe.studycaferenewal.domain.Member;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface MemberService {

    public String join(Member member);
    public Optional<Member> checkById(Member member);
    public Optional<Member> FindMemberByNameAndPhone(Member member);

}

