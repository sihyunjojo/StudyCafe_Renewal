package studycafe.studycaferenewal.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;
    private String userName;

    @NotNull
    private String title;
    private String category;
    private String content;
    private String attachmentFile;
    private String popup;
    @NotNull
    private Integer readCount;
    @NotNull
    private Integer likeCount;

}
