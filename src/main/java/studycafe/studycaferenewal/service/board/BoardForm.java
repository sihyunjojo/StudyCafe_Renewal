package studycafe.studycaferenewal.service.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardForm {
    private Long id;

    private String userName;
    private String title;
    private String category;
    private String content;
    private LocalDateTime createdTime;
    private String attachmentFile;
    private String popup;
    private Integer readCount;
    private Integer likeCount;
}
