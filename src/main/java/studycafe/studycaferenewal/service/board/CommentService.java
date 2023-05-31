package studycafe.studycaferenewal.service.board;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Comment;
import studycafe.studycaferenewal.repository.board.comment.JpaCommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final JpaCommentRepository commentRepository;

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }


    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void editComment(Long commentId, Comment updateComment) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow();

        findComment.setContent(updateComment.getContent());
    }


    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }


    public List<Comment> findByBoardId(Long boardId) {
        return  commentRepository.findByBoardId(boardId);
    }
}
