package studycafe.studycaferenewal.repository;

import studycafe.studycaferenewal.domain.Member;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface MemberRepository {
    //필요없는거 같다
    //public Long getLoginMember(Member member);
    public Member save(Member member);
    public Optional<Member> findByNameAndPhone(String name, Long phone);
    //필요없는 기능같다
    //public Optional<Member> getFindIdMember(Long id);
    //public Long getMemberPassword(Member member); 밑에껄로 대체
    public Optional<Member> findById(String id);
    //필요가 없는 기능같다.
    //public Member getFindPasswordMember(Member member);
    public Optional<Member> findByIdAndPassword(String id, String password);
    public Long getUniqueId(String id);



}
