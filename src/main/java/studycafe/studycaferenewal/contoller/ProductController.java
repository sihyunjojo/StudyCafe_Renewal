package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.domain.Product;
import studycafe.studycaferenewal.repository.product.dto.ProductSearchCond;
import studycafe.studycaferenewal.service.product.ProductService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String products(@ModelAttribute("productSearch") ProductSearchCond productSearch, Model model) {
        //productSearch 검색할때 쓰일것
        List<Product> products = productService.findSearchProducts(productSearch);
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable long productId, Model model) {
        Product product = productService.findById(productId).orElseThrow();
        productService.increaseReadCount(product);
        model.addAttribute("product", product);

        return "product/product";
    }

    @GetMapping("/add")
    public String addForm(@Login Member member, Model model) {
//        if (member == null) {
//            return "redirect:/login";
//        }
        model.addAttribute("product", new Product());
        model.addAttribute("loginMember", member);
        log.info("loginMember={}", member);
        return "product/addProductForm";
    }

    @PostMapping("/add")
    public String add(Product product, Model model) {
        log.info("product={}", product);
        productService.addProduct(product);
        return "redirect:/product"; // 일단 home으로 보내주자 나중에 product목록으로 보내주고
    }

    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId).orElseThrow();
        model.addAttribute("product", product);
        return "product/editProductForm";
    }

    @PostMapping("/{productId}/edit")
    public String edit(Product product, @PathVariable Long productId) {
        productService.updateProduct(productId, product);

        return "redirect:/product";
    }

    @GetMapping("/{productId}/edit/likeCountUp")
    public String upLikeCountEdit(@PathVariable Long productId) {
        productService.upLikeCountProduct(productId);
        return "redirect:/product";
    }

    @GetMapping("/{productId}/edit/likeCountDown")
    public String downLikeCountEdit(@PathVariable Long productId) {
        productService.downLikeCountProduct(productId);
        return "redirect:/product";
    }

    @GetMapping("/{productId}/delete")
    public String delete(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product"; // 삭제 후 목록 페이지로 리다이렉트
    }
}

