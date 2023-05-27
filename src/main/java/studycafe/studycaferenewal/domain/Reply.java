package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long boardId;
    private String userName;
    private String content;
    private LocalDateTime updateTime;
    private Integer likeCount;

    public Reply() {

    }

    public Reply(Long id, Long userId, Long boardId, String userName, String content, LocalDateTime updateTime, Integer likeCount) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.userName = userName;
        this.content = content;
        this.updateTime = updateTime;
        this.likeCount = likeCount;
    }

    @PrePersist
    public void prePersist() {
        if (this.updateTime == null) {
            this.updateTime = LocalDateTime.now();
        }
    }

}
