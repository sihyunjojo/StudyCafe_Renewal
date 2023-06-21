package studycafe.studycaferenewal.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;
    @NotNull
    private Long boardId;
    private String userName;
    private String content;
    private Integer likeCount;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies;


}
