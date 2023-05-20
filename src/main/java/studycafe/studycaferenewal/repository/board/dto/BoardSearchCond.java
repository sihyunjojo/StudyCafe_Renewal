package studycafe.studycaferenewal.repository.board.dto;

import lombok.Data;

@Data
public class BoardSearchCond {
    private String title;
    private String userName;
    private String type;

    public BoardSearchCond(String title, String userName, String type) {
        this.title = title;
        this.userName = userName;
        this.type = type;
    }
}
