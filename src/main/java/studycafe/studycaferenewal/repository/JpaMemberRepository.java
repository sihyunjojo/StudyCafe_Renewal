package studycafe.studycaferenewal.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // Member DB내부에 저장시켜줌.
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
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
    public Optional<Member> findById(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id",id)
                .getResultList();

        return result.stream().findAny();
    }

    // 아이디 패스워드를 통해서 멤버 return해줌
    @Override
    public Optional<Member> findByIdAndPassword(String id, String password) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id and m.password = :password", Member.class)
                .setParameter("id",id).setParameter("password",password)
                .getResultList();

        return result.stream().findAny();
    }

    // 같은 아이디가 몇개 있는지 알아봐서 아이디 중복확인 할때 사용
    @Override
    public Long getUniqueId(String id) {
        Long result =  em.createQuery("select count(*) from Member m where m.id = :id", Member.class)
                .setParameter("id",id)
                .getResultList().stream().count();
        return result;
    }
}
