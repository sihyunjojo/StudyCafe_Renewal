package studycafe.studycaferenewal.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Long userId;
    private String userName;

    @NotEmpty
    private String title;
    private String category;
    private String content;
    private String attachmentFile;
    private String popup;
    @NotEmpty
    private Integer readCount;
    @NotEmpty
    private Integer likeCount;

}
