package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.resolver.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.domain.Product;
import studycafe.studycaferenewal.repository.product.dto.ProductSearchCond;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.product.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final ProductService productService;

    @GetMapping("/")
    public String home(HttpServletRequest request, @Login Member loginMember, @ModelAttribute("productSearch") ProductSearchCond productSearch, Model model) {
        setupPopup(request);

        if (loginMember != null) {
            model.addAttribute(LOGIN_MEMBER, loginMember);
        }

        List<Board> boards = boardService.getHomeBoards();
        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);

        List<Product> products = productService.findProductsTop5LikeCount(productSearch);
        model.addAttribute("products", products);

        return "home";
    }

    private static void setupPopup(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("popupShown") && cookie.getValue().equals("true")) {
                    // 이미 팝업을 보았으면 홈 화면으로 이동
                    log.info("cookie={},{}", cookie.getValue(), cookie.getName());
                }
            }
        }
    }

}