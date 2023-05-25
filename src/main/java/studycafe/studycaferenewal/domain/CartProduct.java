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
    private LocalDateTime updateTime;

    public CartProduct() {
    }

    public CartProduct(Long id, Long cartId, Long productId, Integer quantity, Integer totalPrice, LocalDateTime updateTime) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.updateTime = updateTime;
    }

    @PrePersist
    public void prePersist() {
        if (this.updateTime == null) {
            this.updateTime = LocalDateTime.now();
        }
    }
}
