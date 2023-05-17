package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userLoginId;
    private String title;
    private String kind;
    private String createdDate;
    private String content;
    private String attachmentFile;
    private String popup;
    private Integer readCount;
    private Integer pageNumber;

    public Board() {

    }

    public Board(Long id, String userLoginId, String title, String kind, String createdDate, String content, String attachmentFile, String popup, Integer readCount, Integer pageNumber) {
        this.id = id;
        this.userLoginId = userLoginId;
        this.title = title;
        this.kind = kind;
        this.createdDate = createdDate;
        this.content = content;
        this.attachmentFile = attachmentFile;
        this.popup = popup;
        this.readCount = readCount;
        this.pageNumber = pageNumber;
    }
}
