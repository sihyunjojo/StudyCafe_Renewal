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
import studycafe.studycaferenewal.domain.Reply;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.board.CommentService;
import studycafe.studycaferenewal.service.board.ReplyService;

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
        return "board/board";
    }

    // 댓글 생성
    @PostMapping()
    public String add(Reply reply) {
        replyService.addReply(reply);
        return "redirect:/board"; // 일단 home으로 보내주자 나중에 board목록으로 보내주고
    }

    // 댓글 수정
    @PostMapping("/{replyId}/edit")
    public String edit(Reply Reply, @PathVariable Long replyId) {
        replyService.editReply(replyId, Reply);
        return "redirect:/board";
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
