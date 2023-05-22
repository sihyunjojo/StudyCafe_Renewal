package studycafe.studycaferenewal.repository.cart_product;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.CartProduct;

public interface JpaCartProductRepository extends JpaRepository<CartProduct, Long> {

}
