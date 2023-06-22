package studycafe.studycaferenewal.service.member;

import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.dto.MemberUpdateForm;

import java.util.Optional;

public interface MemberService {

    public String join(Member member);
    public Optional<Member> findById(Member member);
    public Optional<Member> findByUserId(Member member);
    public Optional<Member> findByEmailAndProvider(Object email, Object provider);
    public Optional<Member> findMemberByNameAndPhone(Member member);
    public Optional<Member> update(long memberId, MemberUpdateForm updateForm);

}

