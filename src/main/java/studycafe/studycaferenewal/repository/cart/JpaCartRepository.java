package studycafe.studycaferenewal.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Cart;
import studycafe.studycaferenewal.domain.Member;

import java.util.Optional;

public interface JpaCartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findFirstByUserId(Long UserId);

}
