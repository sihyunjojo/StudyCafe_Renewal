package studycafe.studycaferenewal.repository.board.board;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Board;

import java.util.List;



public interface JpaBoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedTimeDesc();
    List<Board> findAllByCategoryOrderByCreatedTimeDesc(String category);


}
