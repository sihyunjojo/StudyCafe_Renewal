package studycafe.studycaferenewal.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import studycafe.studycaferenewal.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static studycafe.studycaferenewal.domain.QMember.member;

@Slf4j
@Repository
public class JpaQueryMemberRepository implements MemberRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaQueryMemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //이름과 휴대폰번호로 멤버를 찾아주는 (이미 있는 회원입니다.) 음.. 필요없는거 같은데 우선 나둬바바
    @Override
    public Optional<Member> findByNameAndPhone(String name, String phone) {


        List<Member> result = em.createQuery("select m from Member m where m.name = :name and m.phone = :phone", Member.class)
                .setParameter("name",name).setParameter("phone",phone)
                .getResultList();

        return result.stream().findAny();
    }

    //id를 통해서 멤버 리턴해줌
    @Override
    public Optional<Member> findByUserId(String userId) {
        List<Member> result = query
            .select(member) //static import QItem. 지운거임
            .from(member)
            .where(member.userId.eq(userId))
            .fetch();

//        List<Member> result = em.createQuery("select m from Member m where m.userId = :userId", Member.class)
//                .setParameter("userId",userId)
//                .getResultList();

        return result.stream().findAny();
    }

    // 아이디 패스워드를 통해서 멤버 return해줌
    @Override
    public Optional<Member> findByIdAndPassword(String userId, String userPassword) {
        List<Member> result = em.createQuery("select m from Member m where m.userId = :userId and m.userPassword = :userPassword", Member.class)
                .setParameter("userId",userId).setParameter("userPassword",userPassword)
                .getResultList();

        return result.stream().findAny();
    }

    // 같은 아이디가 몇개 있는지 알아봐서 아이디 중복확인 할때 사용
    @Override
    public Long getUniqueId(String userId) {
        Long result =  em.createQuery("select count(*) from Member m where m.userId = :userId", Member.class)
                .setParameter("userId",userId)
                .getResultList().stream().count();
        return result;
    }
}
