package studycafe.studycaferenewal.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.MemberRepository;
import studycafe.studycaferenewal.repository.member.dto.UpdateMemberDto;

import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class JpaMemberService implements MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public String join(Member member) {
        try {
            validateDuplicatedMember(member); // 중복회원 검증
        } catch (IllegalStateException e) {
            return null;
        }
        memberRepository.save(member); // db에 멤버값 넣어줌
        return member.getUserId();
    }

    //public Member viewMember(Member member);


    @Override
    public Optional<Member> findById(Member member) {
        return memberRepository.findById(member.getId());
    }

    public Optional<Member> findByUserId(Member member){
        return memberRepository.findByUserId(member.getUserId());
    }

    public Optional<Member> findMemberByNameAndPhone(Member member){
        return memberRepository.findByNameAndPhone(member.getName(), member.getPhone());
    }

    @Override
    public Optional<Member> update(Member loginMember, UpdateMemberDto UpdateMember) {

        return null;
    }


    //public int checkMemberPassword(Member member);
    //public Member getFindPasswordMember(Member member);
    //public int checkUniqueId(Member member);


    // memeber의 이름과 같은걸 찾아서
    private void validateDuplicatedMember(Member member) {
        log.info("userId = {}", member.getUserId());
        memberRepository.findByUserId(member.getUserId())// member과 같은 이름이 있는지 찾앗을때
                .ifPresent(member1 -> {    // null이 아니니까(값이존재하면 ifPresent()인자 함수 실행
                    throw new IllegalStateException("이미 존재하는 회원입니다.");  //
                });
    }

}
