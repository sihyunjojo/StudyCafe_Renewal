package studycafe.studycaferenewal.repository.product.dto;

import lombok.Data;

@Data
public class ProductSearchCond {
    private String name;
    private String category;
    private Integer price;
    private Integer like_count;

    public ProductSearchCond(String name, String category, Integer price, Integer like_count) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.like_count = like_count;
    }
}
