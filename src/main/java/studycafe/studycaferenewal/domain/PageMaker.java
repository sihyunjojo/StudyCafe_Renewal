package studycafe.studycaferenewal.domain;

import lombok.Data;

@Data
// 이건 페이지 그 자체
public class PageMaker {

    private int totalCount; // 총 몇개의 페이지로 이루워지는지
    private int startPage; // 현재 페이지 ex) 보여주는 10개 중에서 맨 처음
    private int endPage;    // 10개중에서 맨 마지막
    private int currentPage; // 현재 페이지
    private int totalPages; // 10개가 몇개 있는지
    private int displayPageNum; // 보여줄 페이지 개수

    public PageMaker(int totalCount, int currentPage, int displayPageNum) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.displayPageNum = displayPageNum;
        calcData();
    }

    private void calcData() {
        totalPages = (int) Math.ceil((double) totalCount / displayPageNum);
        endPage = ((int) Math.ceil((double) currentPage / displayPageNum)) * displayPageNum;
        startPage = endPage - displayPageNum + 1;

        if (endPage > totalPages) {
            endPage = totalPages;
        }

        if (startPage < 1) {
            startPage = 1;
        }
    }
}
