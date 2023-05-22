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

    private Long userId;
    private Long productId;
    private Integer quantity;
    private Integer price;
    private LocalDateTime updateDate;

    public CartProduct() {
    }

    public CartProduct(Long id, Long userId, Long productId, Integer quantity, Integer price, LocalDateTime updateDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.updateDate = updateDate;
    }

    @PrePersist
    public void prePersist() {
        if (this.updateDate == null) {
            this.updateDate = LocalDateTime.now();
        }
    }
}
