package studycafe.studycaferenewal.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studycafe.studycaferenewal.domain.Product;
import studycafe.studycaferenewal.repository.product.JpaProductQueryRepository;
import studycafe.studycaferenewal.repository.product.JpaProductRepository;
import studycafe.studycaferenewal.repository.product.dto.ProductSearchCond;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final JpaProductRepository productRepository;
    private final JpaProductQueryRepository productQueryRepository;

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<Product> findSearchedProducts(ProductSearchCond cond) {
        return productQueryRepository.findSearchedProducts(cond);
    }

    public List<Product> findSearchedAndSortedProducts(ProductSearchCond cond, String sort) {
        return productQueryRepository.findSearchedAndSortedProducts(cond, sort);
    }

    public List<Product> findProductsTop5LikeCount(ProductSearchCond cond){
        return productQueryRepository.findTop5LikeCountProducts(cond);}

    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(Product product) {
        product.setReadCount(0);
        product.setLikeCount(0);
        productRepository.save(product);
    }

    public void updateProduct(Long productId, Product updateproduct) {
        Product product = productRepository.findById(productId).orElseThrow();

        product.setName(updateproduct.getName());
        product.setCategory(updateproduct.getCategory());
        product.setDescription(updateproduct.getDescription());
        product.setQuantity(updateproduct.getQuantity());
        product.setPrice(updateproduct.getPrice());
        product.setImage(updateproduct.getImage());
        product.setUpdatedTime(LocalDateTime.now());

    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    public void increaseReadCount(Product product) {
        product.setReadCount(product.getReadCount() + 1);
    }

    public void upLikeCountProduct(Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow();
        findProduct.setLikeCount(findProduct.getLikeCount() + 1);
    }

    public void downLikeCountProduct(Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow();
        if (findProduct.getLikeCount() > 0) {
            findProduct.setLikeCount(findProduct.getLikeCount() - 1);
        }
    }
}