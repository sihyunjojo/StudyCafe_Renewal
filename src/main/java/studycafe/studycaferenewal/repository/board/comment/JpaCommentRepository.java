package studycafe.studycaferenewal.repository.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
