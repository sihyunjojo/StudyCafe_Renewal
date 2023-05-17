package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.service.board.BoardService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String board(@ModelAttribute("boardSearch") BoardSearchCond boardSearch, Model model) {
        //boardSearch 검색할때 쓰일것
        List<Board> items = boardService.findBoards();
        model.addAttribute("items", items);
        return "board/boards";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());
        return "/board/addBoardForm";
    }

    @PostMapping("/add")
    public String add(Board board, Model model) {
        log.info("board={}", board);
        boardService.addBoard(board);
        return "redirect:/board"; // 일단 home으로 보내주자 나중에 board목록으로 보내주고
    }

}
