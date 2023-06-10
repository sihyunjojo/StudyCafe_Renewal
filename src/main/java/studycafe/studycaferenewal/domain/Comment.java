package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long boardId;
    private String userName;
    private String content;
    private LocalDateTime createdTime;
    private Integer pageNumber;
    private Integer likeCount;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies;

    @PrePersist
    public void prePersist() {
        this.createdTime = LocalDateTime.now();
    }



    public Comment() {

    }

    public Comment(Long id, Long userId, Long boardId, String userName, String content, LocalDateTime createdTime, Integer pageNumber, Integer likeCount) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.userName = userName;
        this.content = content;
        this.createdTime = createdTime;
        this.pageNumber = pageNumber;
        this.likeCount = likeCount;
    }



}
