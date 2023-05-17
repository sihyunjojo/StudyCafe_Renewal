package studycafe.studycaferenewal.service.member;

import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.dto.UpdateMemberDto;

import java.util.Optional;

public interface MemberService {

    public String join(Member member);
    public Optional<Member> findById(Member member);
    public Optional<Member> findByUserId(Member member);
    public Optional<Member> findMemberByNameAndPhone(Member member);
    public Optional<Member> update(Member loginmember, UpdateMemberDto updateMember);

}

