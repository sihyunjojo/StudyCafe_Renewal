package studycafe.studycaferenewal.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.JpaBoardRepository;
import studycafe.studycaferenewal.repository.member.JpaMemberRepository;

import java.time.LocalDateTime;
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

    public void updateBoard(Board board, BoardForm boardForm) {
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setAttachmentFile(boardForm.getAttachmentFile());
        board.setCreatedDate(board.getCreatedDate()); //이거 자동으로 되게 못하

    }


    public List<BoardForm> boardsToBoardForms(List<Board> boards) {
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

    public BoardForm boardToBoardForm(Board board) {
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

    public Board boardFormToBoard(BoardForm boardForm) {
        Board board = new Board();
        board.setId(boardForm.getId());
        board.setUserId(boardRepository.findById(boardForm.getId()).orElseThrow().getUserId()); //boardform의 id와 board의 id가 같으니까 같은 board를 가져와서, board의 getuserid

        board.setTitle(boardForm.getTitle());
        board.setKind(boardForm.getKind());
        board.setContent(boardForm.getContent());
        board.setCreatedDate(boardForm.getCreatedDate());
        board.setAttachmentFile(boardForm.getAttachmentFile());
        board.setPopup(boardForm.getPopup());
        board.setReadCount(boardForm.getReadCount());
        board.setPageNumber(boardForm.getPageNumber());

        return board;
    }

}
