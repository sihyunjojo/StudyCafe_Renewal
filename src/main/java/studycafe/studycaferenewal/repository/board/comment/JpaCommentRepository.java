package studycafe.studycaferenewal.repository.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Comment;

import java.util.List;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId(Long boardId);
}
