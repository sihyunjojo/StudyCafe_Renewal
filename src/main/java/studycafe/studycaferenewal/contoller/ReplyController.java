package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Comment;
import studycafe.studycaferenewal.resolver.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.domain.Reply;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.board.CommentService;
import studycafe.studycaferenewal.service.board.ReplyService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final ReplyService replyService;

    //헤당 comment와 관련 댓글들 전부 불러오기
    @GetMapping("/{commentId}")
    public String reply(@PathVariable long commentId, Model model) {
        List<Reply> replys = replyService.findReplys();

        return "redirect:/board/" + replys.get(0).getComment().getBoardId();
    }

    @GetMapping("/{boardId}")
    public String goToBoard(@Login Member loginMember, @PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        log.info("board ={}", board);
        boardService.increaseReadCount(board);
        BoardForm boardForm = boardService.boardToBoardForm(board);

        List<Comment> comments = commentService.findByBoardId(boardId);

        List<Reply> AllReplys = new ArrayList<>();
        for (Comment comment : comments) {
            List<Reply> replies = replyService.getRepliesByCommentId(comment.getId()); // 해당 댓글에 대한 답변 목록 조회
            comment.setReplies(replies); // 댓글 객체에 답변 목록 설정
        }

        model.addAttribute("loginMember", loginMember);
        model.addAttribute("board", boardForm);
        model.addAttribute("comments", comments);
        return "board/board";
    }

    // 댓글 생성
    @PostMapping("/{boardId}")
    public String add(@Login Member loginMember, Reply reply) {
        log.info("loginMember = {}", loginMember);
        log.info("reply = {}", reply);
        if (loginMember == null) {
            return "redirect:/login?redirectURL=/board/" + reply.getComment().getBoardId();
        }
        replyService.addReply(reply);
        return "redirect:/board/" + reply.getComment().getBoardId();
    }

    // 댓글 수정
    @PostMapping("/{replyId}/edit")
    public String edit(Reply reply, @PathVariable Long replyId) {
        replyService.editReply(replyId, reply);
        return "redirect:/board/" + reply.getComment().getBoardId();
    }

//    @PostMapping("/{boardId}/edit/likeCount")
//    public String editLikeCount(BoardForm boardForm, @PathVariable Long boardId) {
//        // 이걸하려면 멤버마다 그 보드에 대한 like를 유지하고 있는지에 대한 db 컬럼이 필요하다.
//        return "redirect:/board";
//    }

    // 댓글 삭제
    @GetMapping("/{replyId}/delete")
    public String delete(@PathVariable long replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/board"; // 삭제 후 목록 페이지로 리다이렉트
    }
}
