package studycafe.studycaferenewal.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long boardId;
    private String userName;
    private String content;
    private Integer pageNumber;
    private Integer likeCount;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies;


}
