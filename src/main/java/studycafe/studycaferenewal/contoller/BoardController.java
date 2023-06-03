package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.*;
import studycafe.studycaferenewal.repository.board.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.board.CommentService;
import studycafe.studycaferenewal.service.board.ReplyService;

import java.util.ArrayList;
import java.util.List;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final ReplyService replyService;

    private static final int DISPLAY_PAGE_NUM = 2;    // 페이지 번호 표시 개수, 나중에 값 바꾸는 기능 넣기
    // 실험중
    @GetMapping()
    public String boards(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, @RequestParam(required = false, defaultValue = "1") int page, Model model) {
        List<Board> boards = boardService.findBoards();

        List<Board> boardList = boardService.getBoardList(page, DISPLAY_PAGE_NUM); //이게 맞나?

        List<BoardForm> boardForms = boardService.boardsToBoardForms(boardList);
        model.addAttribute("boards", boardForms);

        // service
        log.info("page = {}", page);

        int totalCount = boards.size();

        PageMaker pageMaker = new PageMaker(totalCount, page, DISPLAY_PAGE_NUM);
        log.info("pagemarker = {}", pageMaker);
        model.addAttribute("pageMaker", pageMaker);

        return "board/boards";
    }

    //@GetMapping()
    public String boards(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, Model model) {
        List<Board> boards = boardService.findBoards();
        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);
        return "board/boards";
    }

    @GetMapping("/search")
    public String searchBoards(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, @RequestParam(required = false) String sort, Model model) {
        List<Board> boards;

        if (sort.isEmpty()) {
            boards = boardService.findSearchBoards(boardSearch);
        } else{
            boards = boardService.findSearchedAndSortedBoards(boardSearch, sort);
        }

        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);
        model.addAttribute("boardSearch", boardSearch);

        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@Login Member loginMember, @PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        boardService.increaseReadCount(board);
        BoardForm boardForm = boardService.boardToBoardForm(board);

        List<Comment> comments = commentService.findByBoardId(boardId);

        List<Reply> AllReplys= new ArrayList<>();
        for (Comment comment : comments) {
            List<Reply> replies = replyService.getRepliesByCommentId(comment.getId()); // 해당 댓글에 대한 답변 목록 조회
            comment.setReplies(replies); // 댓글 객체에 답변 목록 설정
        }

        model.addAttribute("loginMember", loginMember);
        model.addAttribute("board", boardForm);
        model.addAttribute("comments", comments);
        return "board/board";
    }

    @GetMapping("/add")
    public String addForm(@Login Member loginMember, Model model) {
        if (loginMember == null) {
            return "redirect:/login?redirectURL=/board/add";
        }
        model.addAttribute("board", new Board());
        model.addAttribute(LOGIN_MEMBER, loginMember);
        log.info("loginMember={}", loginMember);
        return "board/addBoardForm";
    }

    @PostMapping("/add")
    public String add(Board board) {
        log.info("board={}", board);
        boardService.addBoard(board);
        return "redirect:/board"; // 일단 home으로 보내주자 나중에 board목록으로 보내주고
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        BoardForm boardForm = boardService.boardToBoardForm(board);
        model.addAttribute("board", boardForm);
        return "board/editBoardForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(BoardForm boardForm, @PathVariable Long boardId) {
//        Board board = boardService.boardFormToBoard(boardForm);
        boardService.updateBoard(boardId, boardForm);

        return "redirect:/board";
    }

//    @PostMapping("/{boardId}/edit/likeCount")
//    public String editLikeCount(BoardForm boardForm, @PathVariable Long boardId) {
//        // 이걸하려면 멤버마다 그 보드에 대한 like를 유지하고 있는지에 대한 db 컬럼이 필요하다.
//        return "redirect:/board";
//    }

    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable long boardId) {

        boardService.deleteBoard(boardId);
        return "redirect:/board"; // 삭제 후 목록 페이지로 리다이렉트
    }
}
