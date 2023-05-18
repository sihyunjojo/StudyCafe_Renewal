package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, Model model) {
        //boardSearch 검색할때 쓰일것
        List<Board> boards = boardService.findBoards();
        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);
        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        BoardForm boardForm = boardService.boardToBoardForm(board);
        model.addAttribute("board", boardForm);
        return "board/board";
    }

    @GetMapping("/add")
    public String addForm(@Login Member member, Model model) {
        model.addAttribute("board", new Board());
        model.addAttribute("loginMember", member);
        log.info("loginmember={}", member);
        return "board/addBoardForm";
    }

    @PostMapping("/add")
    public String add(Board board, Model model) {
        log.info("board={}", board);
        boardService.addBoard(board);
        return "redirect:/board"; // 일단 home으로 보내주자 나중에 board목록으로 보내주고
    }

    @GetMapping("/{boardId}/edit")
    public String EditForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.findById(boardId).orElseThrow();
        BoardForm boardForm = boardService.boardToBoardForm(board);
        model.addAttribute("board", boardForm);
        return "board/editBoardForm";
    }

    @PostMapping("/{boardId}/edit")
    public String Edit(BoardForm boardForm, @PathVariable Long boardId){
        Board board = boardService.boardFormToBoard(boardForm);
        boardService.updateBoard(board,boardForm);

        return "redirect:board/board";
    }
}