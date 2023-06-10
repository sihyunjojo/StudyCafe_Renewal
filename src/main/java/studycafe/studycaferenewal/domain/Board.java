package studycafe.studycaferenewal.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String title;
    private String category;
    private String content;
    private String attachmentFile;
    private String popup;
    private Integer readCount;
    private Integer likeCount;

}
