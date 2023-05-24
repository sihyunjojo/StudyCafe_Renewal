package studycafe.studycaferenewal.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.CartProduct;

import java.util.List;
import java.util.Optional;

public interface JpaCartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findAllByCartId(Long id);

    Optional<CartProduct> findFirstByCartIdAndProductId(Long cartId, Long productId);
}
