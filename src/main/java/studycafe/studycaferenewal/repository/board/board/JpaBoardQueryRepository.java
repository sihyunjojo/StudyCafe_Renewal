package studycafe.studycaferenewal.repository.board.board;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.repository.board.board.dto.BoardSearchCond;

import javax.persistence.EntityManager;
import java.util.List;

import static studycafe.studycaferenewal.domain.QBoard.board;

public class JpaBoardQueryRepository {
    private final JPAQueryFactory query;

    public JpaBoardQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    public List<Board> findAll(BoardSearchCond cond) {
        return query.select(board)
                .from(board)
                .where(
                        likeBoardTitle(cond.getTitle()),
                        likeBoardCreatedUserName(cond.getUserName()),
                        eqBoardCategory(cond.getCategory())
                )
                .fetch();
    }

    private BooleanExpression likeBoardTitle(String boardName) {
        if (StringUtils.hasText(boardName)) {
            return board.title.like("%" + boardName + "%");
        }
        return null;
    }

    private BooleanExpression likeBoardCreatedUserName(String userName) {
        if (StringUtils.hasText(userName)) {
            return board.userName.like("%" + userName + "%");
        }
        return null;
    }

    private Predicate eqBoardCategory(String category) {
        if (StringUtils.hasText(category)) {
            return board.category.eq(category);
        }
        return null;
    }


}
