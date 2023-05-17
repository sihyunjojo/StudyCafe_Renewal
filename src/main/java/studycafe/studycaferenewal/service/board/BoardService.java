package studycafe.studycaferenewal.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.JpaBoardRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final JpaBoardRepository boardRepository;

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public void addBoard(Board board) {
        boardRepository.save(board);
    }
}
