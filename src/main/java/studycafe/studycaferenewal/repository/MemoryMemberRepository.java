package studycafe.studycaferenewal.repository;

import org.springframework.stereotype.Repository;
import studycafe.studycaferenewal.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<String, Member> store = new HashMap<>();//static
    private static long sequence = 0L; //static

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByNameAndPhone(String name, String phone) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByIdAndPassword(String id, String password) {
        return Optional.empty();
    }

    @Override
    public Long getUniqueId(String id) {
        return null;
    }
}
