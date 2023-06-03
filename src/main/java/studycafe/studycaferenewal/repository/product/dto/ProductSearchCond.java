package studycafe.studycaferenewal.repository.product.dto;

import lombok.Data;

@Data
public class ProductSearchCond {
    private String name;
    private String category;
    private Integer maxPrice;
    private Integer minPrice;
    private Integer minLikeCount;
    private String sort;

    public ProductSearchCond(String name, String category, Integer maxPrice, Integer minPrice, Integer minLikeCount, String sort) {
        this.name = name;
        this.category = category;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.minLikeCount = minLikeCount;
        this.sort = sort;
    }
}
