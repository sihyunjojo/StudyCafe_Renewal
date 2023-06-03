package studycafe.studycaferenewal.repository.board.board.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BoardSearchCond {
    private String title;
    private String userName;
    private String category;
    private String sort;

    public BoardSearchCond(String title, String userName, String category, String sort) {
        this.title = title;
        this.userName = userName;
        this.category = category;
        this.sort = sort;
    }
}
