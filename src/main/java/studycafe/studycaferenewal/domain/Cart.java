package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;;
    private LocalDateTime updatedTime;

    public Cart() {
    }

    public Cart(Long id, Long userId, LocalDateTime updatedTime) {
        this.id = id;
        this.userId = userId;
        this.updatedTime = updatedTime;
    }

    @PrePersist
    public void prePersist() {
        this.updatedTime = LocalDateTime.now();
    }
}
