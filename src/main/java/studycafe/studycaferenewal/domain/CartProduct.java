package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private Long productId;
    private Integer quantity;
    private Integer totalPrice;
    private LocalDateTime updatedTime;

    public CartProduct() {
    }

    public CartProduct(Long id, Long cartId, Long productId, Integer quantity, Integer totalPrice, LocalDateTime updatedTime) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.updatedTime = updatedTime;
    }

    @PrePersist
    public void prePersist() {
        if (this.updatedTime == null) {
            this.updatedTime = LocalDateTime.now();
        }
    }
}

