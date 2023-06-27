package studycafe.studycaferenewal.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Member;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findFirstByUserLoginId(String UserId);
    Optional<Member> findByEmailAndProvider(String email, String provider);
    Optional<Member> findFirstByNameAndPhone(String name, String phone);
}
