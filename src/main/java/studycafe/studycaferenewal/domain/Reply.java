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
    private String userName;
    private String content;
    private LocalDateTime updatedTime;
    private Integer likeCount;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;


    @PrePersist
    public void prePersist() {
        this.updatedTime = LocalDateTime.now();
    }


    public Reply() {

    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", updatedTime=" + updatedTime +
                ", likeCount=" + likeCount +
                ", comment=" + comment.getId() +
                '}';
    }
}
