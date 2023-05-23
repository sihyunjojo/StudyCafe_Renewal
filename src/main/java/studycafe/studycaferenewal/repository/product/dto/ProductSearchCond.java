package studycafe.studycaferenewal.repository.product.dto;

import lombok.Data;

@Data
public class ProductSearchCond {
    private String name;
    private String category;
    private Integer maxPrice;
    private Integer minPrice;
    private Integer like_count;

    public ProductSearchCond(String name, String category, Integer maxPrice, Integer minPrice, Integer like_count) {
        this.name = name;
        this.category = category;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.like_count = like_count;
    }
}
