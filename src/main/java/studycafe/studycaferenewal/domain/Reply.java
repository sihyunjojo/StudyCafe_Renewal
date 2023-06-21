package studycafe.studycaferenewal.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Reply extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;
    private String userName;
    private String content;
    private Integer likeCount;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;


}
