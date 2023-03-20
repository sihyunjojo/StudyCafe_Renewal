package studycafe.studycaferenewal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public String join(Member member) {
        validateDuplicatedMember(member); // 중복회원 검증
        memberRepository.save(member); // db에 멤버값 넣어줌
        return member.getId();
    }

    //session에 id와 name 값을 설정해주는 메서드
    public void checkMember(Member member, HttpSession session){
        validateDuplicatedMember(member);
        session.setAttribute("id",member.getId());
        session.setAttribute("name",member.getName());
    }

    //public Member viewMember(Member member);

    public Optional<Member> checkById(Member member){
        return memberRepository.findById(member.getId());
    }

    public Optional<Member> FindMemberByNameAndPhone(Member member){
        return memberRepository.findByNameAndPhone(member.getName(), member.getPhone());
    }

    //public int checkMemberPassword(Member member);
    //public Member getFindPasswordMember(Member member);

    //세션을 없애고 세션에 속해있던 값들 삭제함
    public void logoutMember(HttpSession session){
        session.invalidate();
    }

    //public int checkUniqueId(Member member);
    // memeber의 이름과 같은걸 찾아서
    // ifPresent()는 null이 아니면 실행해줌.
    private void validateDuplicatedMember(Member member) {
        memberRepository.findById(member.getId())   // member과 같은 이름이 있는지 찾앗을때
                .ifPresent(member1 -> {    // null이 아니니까(값이존재하면 ifPresent()인자 함수 실행
                    throw new IllegalStateException("이미 존재하는 회원입니다.");  //
                });
    }

}
