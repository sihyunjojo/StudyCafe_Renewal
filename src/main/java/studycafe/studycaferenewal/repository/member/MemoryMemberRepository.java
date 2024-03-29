package studycafe.studycaferenewal.repository.member;

import org.springframework.stereotype.Repository;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<String, Member> store = new HashMap<>();//static
    private static long sequence = 0L; //static

    @Override
    public Member save(Member member) {
        store.put(member.getUserLoginId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByNameAndPhone(String name, String phone) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUserId(String id) {
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
