package studycafe.studycaferenewal.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import studycafe.studycaferenewal.domain.Board;


public interface JpaBoardRepository extends JpaRepository<Board, Long> {

}
