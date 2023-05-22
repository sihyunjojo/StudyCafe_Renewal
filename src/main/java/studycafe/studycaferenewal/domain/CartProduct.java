package studycafe.studycaferenewal.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

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
