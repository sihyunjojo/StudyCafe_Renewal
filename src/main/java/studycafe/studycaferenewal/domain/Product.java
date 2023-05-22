package studycafe.studycaferenewal.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String image;
    private Integer quantity;
    private Integer price;
    private String description;
    private Integer readCount;
    private Integer likeCount;
    private LocalDateTime updateTime;

    public Product() {

    }

    public Product(Long id, String name, String category, String image, Integer quantity, Integer price, String description, Integer readCount, Integer likeCount, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.readCount = readCount;
        this.likeCount = likeCount;
        this.updateTime = updateTime;
    }
}
