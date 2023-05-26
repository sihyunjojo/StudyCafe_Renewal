package studycafe.studycaferenewal.repository.board.dto;

import lombok.Data;

@Data
public class BoardSearchCond {
    private String title;
    private String userName;
    private String category;

    public BoardSearchCond(String title, String userName, String category) {
        this.title = title;
        this.userName = userName;
        this.category = category;
    }
}
