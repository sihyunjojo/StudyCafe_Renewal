package studycafe.studycaferenewal.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Member;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findFirstByUserId(String UserId);

    Optional<Member> findFirstByNameAndPhone(String name, String phone);
}
