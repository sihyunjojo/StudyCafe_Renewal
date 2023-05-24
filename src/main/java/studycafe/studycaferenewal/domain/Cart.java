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
    private LocalDateTime updateTime;

    public Cart() {
    }

    public Cart(Long id, Long userId, LocalDateTime updateTime) {
        this.id = id;
        this.userId = userId;
        this.updateTime = updateTime;
    }

    @PrePersist
    public void prePersist() {
        if (this.updateTime == null) {
            this.updateTime = LocalDateTime.now();
        }
    }
}
