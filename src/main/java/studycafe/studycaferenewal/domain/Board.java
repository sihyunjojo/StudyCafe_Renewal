package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String title;
    private String kind;
    private LocalDateTime createdDate;
    private String content;
    private String attachmentFile;
    private String popup;
    private Integer readCount;
    private Integer pageNumber;

    public Board() {

    }

    public Board(Long id, Long userId, String title, String kind, LocalDateTime createdDate, String content, String attachmentFile, String popup, Integer readCount, Integer pageNumber) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.kind = kind;
        this.createdDate = createdDate;
        this.content = content;
        this.attachmentFile = attachmentFile;
        this.popup = popup;
        this.readCount = readCount;
        this.pageNumber = pageNumber;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

}
