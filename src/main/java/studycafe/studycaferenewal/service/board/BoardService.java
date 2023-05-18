package studycafe.studycaferenewal.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.JpaBoardRepository;
import studycafe.studycaferenewal.repository.member.JpaMemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final JpaBoardRepository boardRepository;
    private final JpaMemberRepository memberRepository;

    public List<Board> findBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public void addBoard(Board board) {
        boardRepository.save(board);
    }

    public Optional<Board> findById(long boardId) {
        return boardRepository.findById(boardId);
    }

    public List<BoardForm> getBoardForms(List<Board> boards) {
        List<BoardForm> boardForms = new ArrayList<>();

        log.info("board={}", boards);
        for (Board board : boards) {
            BoardForm boardForm = new BoardForm();
            boardForm.setId(board.getId());
            boardForm.setUserName(memberRepository.findById(board.getUserId()).orElseThrow().getName());

            boardForm.setTitle(board.getTitle());
            boardForm.setKind(board.getKind());
            boardForm.setContent(board.getContent());
            boardForm.setCreatedDate(board.getCreatedDate());
            boardForm.setAttachmentFile(board.getAttachmentFile());
            boardForm.setPopup(board.getPopup());
            boardForm.setReadCount(board.getReadCount());
            boardForm.setPageNumber(board.getPageNumber());
            boardForms.add(boardForm);
        }
        return boardForms;
    }

    public BoardForm getBoardForm(Board board) {
        BoardForm boardForm = new BoardForm();
        boardForm.setId(board.getId());
        boardForm.setUserName(memberRepository.findById(board.getUserId()).orElseThrow().getName());

        boardForm.setTitle(board.getTitle());
        boardForm.setKind(board.getKind());
        boardForm.setContent(board.getContent());
        boardForm.setCreatedDate(board.getCreatedDate());
        boardForm.setAttachmentFile(board.getAttachmentFile());
        boardForm.setPopup(board.getPopup());
        boardForm.setReadCount(board.getReadCount());
        boardForm.setPageNumber(board.getPageNumber());

        return boardForm;
    }

}
