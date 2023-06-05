package studycafe.studycaferenewal.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.board.JpaBoardQueryRepository;
import studycafe.studycaferenewal.repository.board.board.JpaBoardRepository;
import studycafe.studycaferenewal.repository.board.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.repository.member.JpaMemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final JpaBoardRepository boardRepository;
    private final JpaMemberRepository memberRepository;
    private final JpaBoardQueryRepository boardQueryRepository;


    public List<Board> getHomeBoards() {
        List<Board> boardsWithoutNotice = boardRepository.findAllByCategoryNotOrderByCreatedTimeDesc("공지사항");
        List<Board> notices = boardRepository.findAllByCategoryOrderByCreatedTimeDesc("공지사항");
        List<Board> Top3Notices = notices.subList(0, 3);

        List<Board> homeBoards = boardsWithoutNotice;
        homeBoards.addAll(0, Top3Notices);

        return homeBoards;
    }

    public List<Board> getBoardList(int page, int perPageNum, List<Board> boards) { // 현재페이지, 페이지당 몇개를 보여주는지
        int startBoard = (page - 1) * perPageNum;
        int endBoard = Math.min(page * perPageNum, boards.size());

        return boards.subList(startBoard, endBoard);
    }

    public List<Board> getBoardsByCategoryNotOrderByCreatedTimeDesc(String category) {
        return boardRepository.findAllByCategoryNotOrderByCreatedTimeDesc(category);
    }

    public List<Board> getBoardsByCategoryByCreatedTimeDesc(String category) {
        return boardRepository.findAllByCategoryOrderByCreatedTimeDesc(category);
    }


    private void boardsToUpNoticeBoards(List<Board> boards) {
        List<Board> notices = new ArrayList<>();

        Iterator<Board> iterator = boards.iterator(); // 이걸 사용하지 않으면 반복문이 돌면서 사라지는 것때문에 오류 일으킴.
        while (iterator.hasNext()) {
            Board board = iterator.next();
            if (board.getCategory().equals("공지사항")) {
                iterator.remove();
                notices.add(notices.size(), board);
            }
        }
        boards.addAll(0, notices);
    }

    //boards에서 사용
    public List<Board> getSearchedAndSortedBoards(BoardSearchCond cond) {
        return boardQueryRepository.findSearchedAndSortedBoards(cond);
    }


    public void addBoard(Board board) {
        board.setLikeCount(0);
        board.setReadCount(0);
        board.setUserName(memberRepository.findById(board.getUserId()).orElseThrow().getName());
        boardRepository.save(board);
    }

    public Optional<Board> findById(long boardId) {
        return boardRepository.findById(boardId);
    }

    public void updateBoard(Long boardId, BoardForm boardForm) {
        Board board = boardRepository.findById(boardId).orElseThrow();

        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setAttachmentFile(boardForm.getAttachmentFile());
        board.setCreatedTime(LocalDateTime.now());
    }

    public void deleteBoard(long boardId) {
        boardRepository.deleteById(boardId);
    }

    public void increaseReadCount(Board board) {
        board.setReadCount(board.getReadCount() + 1);
    }

    public void increaseLikeCount(Board board) {
        board.setLikeCount(board.getLikeCount() + 1);
    }

    public void decreaseLikeCount(Board board) {
        board.setLikeCount(board.getLikeCount() - 1);
    }

    public List<BoardForm> boardsToBoardForms(List<Board> boards) {
        List<BoardForm> boardForms = new ArrayList<>();

        for (Board board : boards) {
            BoardForm boardForm = new BoardForm();
            boardForm.setId(board.getId());
            boardForm.setUserName(memberRepository.findById(board.getUserId()).orElseThrow().getName());

            boardForm.setTitle(board.getTitle());
            boardForm.setCategory(board.getCategory());
            boardForm.setContent(board.getContent());
            boardForm.setCreatedTime(board.getCreatedTime());
            boardForm.setAttachmentFile(board.getAttachmentFile());
            boardForm.setPopup(board.getPopup());
            boardForm.setReadCount(board.getReadCount());
            boardForm.setLikeCount(board.getLikeCount());
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
        boardForm.setCategory(board.getCategory());
        boardForm.setContent(board.getContent());
        boardForm.setCreatedTime(board.getCreatedTime());
        boardForm.setAttachmentFile(board.getAttachmentFile());
        boardForm.setPopup(board.getPopup());
        boardForm.setReadCount(board.getReadCount());
        boardForm.setLikeCount(board.getLikeCount());
        boardForm.setPageNumber(board.getPageNumber());

        return boardForm;
    }

//    public Board boardFormToBoard(BoardForm boardForm) {
//
//        Board board = new Board();
//        board.setId(boardForm.getId());
//        board.setUserId(boardRepository.findById(boardForm.getId()).orElseThrow().getUserId()); //boardform의 id와 board의 id가 같으니까 같은 board를 가져와서, board의 getuserid
//
//        board.setTitle(boardForm.getTitle());
//        board.setCategory(boardForm.getCategory());
//        board.setContent(boardForm.getContent());
//        board.setCreatedTime(boardForm.getCreatedTime());
//        board.setAttachmentFile(boardForm.getAttachmentFile());
//        board.setPopup(boardForm.getPopup());
//        board.setReadCount(boardForm.getReadCount());
//        board.setLikeCount(boardForm.getLikeCount());
//        board.setPageNumber(boardForm.getPageNumber());
//
//        return board;
//    }
}
