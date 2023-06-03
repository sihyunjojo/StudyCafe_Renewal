package studycafe.studycaferenewal.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Product;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByUpdatedTimeDesc();
}
