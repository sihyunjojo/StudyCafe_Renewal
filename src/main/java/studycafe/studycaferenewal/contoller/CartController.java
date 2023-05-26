package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.CartProduct;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.cart.CartProductForm;
import studycafe.studycaferenewal.service.cart.CartService;

import java.util.List;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;


@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니 보여주기
    @GetMapping
    public String cart(@Login Member member, Model model) {
        if (member == null) {
            return "redirect:/login";
        }
        model.addAttribute(LOGIN_MEMBER, member);

        List<CartProduct> cartProducts = cartService.findCartProducts(member);
        List<CartProductForm> cartProductForms = cartService.cartProductToCartProductForm(cartProducts);
        model.addAttribute("cartProducts", cartProductForms);

        return "cart/cart";
    }

    // 장바구니에 상품 추가
    @GetMapping("/{itemId}/add")
    public String addCartProduct(@Login Member member, @PathVariable long itemId) {
//        if (member == null) {
//            return "redirect:/login";
//        }

        cartService.addCartProduct(member, itemId);

        // 원할때는 장바구니로 보내주는 코드
        // 원하지 않으면 상품 페이지 그대로
        return "redirect:/cart";
    }

    @GetMapping("/{itemId}/edit/up")
    public String editUp(@Login Member member, @PathVariable long itemId) {
//        if (member == null) {
//            return "redirect:/login";
//        }

        cartService.editUpQuantityCartProduct(member, itemId);
        return "redirect:/cart";
        // 여기도 비동기 코드도 해야할거 같기도하고,,
        // 좋아요 같이 해야할거같은데
    }

    @GetMapping("/{itemId}/edit/down")
    public String editDown(@Login Member member, @PathVariable long itemId) {
        if (member == null) {
            return "redirect:/login";
        }

        cartService.editDownQuantityCartProduct(member, itemId);
        return "redirect:/cart";
        // 여기도 비동기 코드도 해야할거 같기도하고,,
        // 좋아요 같이 해야할거같은데
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@Login Member member,@PathVariable long itemId) {
        if (member == null) {
            return "redirect:/login";
        }

        cartService.deleteCartProduct(member, itemId);
        return "redirect:/cart";
        // 여기도 비동기 코드도 해야할거 같기도하고,,
        // 좋아요 같이 해야할거같은데
    }
//
//    // 장바구니에서 수량 변경
//    @GetMapping("/edit")
//    public String editCartProduct(Cart cart, Model model) {
//        // 여기도 좋아요 처럼 ajax로 해서 해야할거 같은데...
//        // 실시간 필요하니까 비동기 코드도 해야될거 같기도 하고
//        return "redirect:/";
//    }
//
    // 장바구니에서 삭제

}
