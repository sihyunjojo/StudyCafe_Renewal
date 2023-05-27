package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.board.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;

import java.util.List;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, Model model) {
        //boardSearch 검색할때 쓰일것
        List<Board> boards = boardService.findSearchBoards(boardSearch);
        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);
        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        boardService.increaseReadCount(board);
        BoardForm boardForm = boardService.boardToBoardForm(board);
        model.addAttribute("board", boardForm);

        return "board/board";
    }

    @GetMapping("/add")
    public String addForm(@Login Member loginMember, Model model) {
        if (loginMember == null) {
            return "redirect:/login?redirectURL=/board/add/";
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
