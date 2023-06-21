package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.resolver.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Comment;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.board.CommentService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    //헤당 board와 관련 댓글들 전부 불러오기
    @GetMapping("/{boardId}")
    public String comments(@PathVariable long boardId, Model model) {
        List<Comment> comments = commentService.findComments();
        return "redirect:/board/" + boardId;
    }

    // 댓글 생성
    @PostMapping("/{boardId}")
    public String add(@Login Member loginMember, @PathVariable long boardId, Comment comment) { // comment 먼가 잘못됬을시 다시 돌려줘야하니까 있어야함.
        log.info("loginMember = {}", loginMember);
        log.info("comment = {}", comment);
        if (loginMember == null) {
            return "redirect:/login?redirectURL=/board/" + boardId;
        }
        commentService.addComment(comment);
        return "redirect:/board/" + boardId;
    }

    // 댓글 수정
    @PostMapping("/{commentId}/edit")
    public String edit(Comment comment, @PathVariable Long commentId) {
        commentService.editComment(commentId, comment);
        return "redirect:/board/" + comment.getBoardId();
    }

//    @PostMapping("/{boardId}/edit/likeCount")
//    public String editLikeCount(BoardForm boardForm, @PathVariable Long boardId) {
//        // 이걸하려면 멤버마다 그 보드에 대한 like를 유지하고 있는지에 대한 db 컬럼이 필요하다.
//        return "redirect:/board";
//    }

    // 댓글 삭제
    @GetMapping("/{commentId}/delete")
    public String delete(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/board"; // 삭제 후 목록 페이지로 리다이렉트
    }
}
