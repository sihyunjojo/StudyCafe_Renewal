package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Board;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.domain.Product;
import studycafe.studycaferenewal.repository.board.dto.BoardSearchCond;
import studycafe.studycaferenewal.service.board.BoardForm;
import studycafe.studycaferenewal.service.board.BoardService;
import studycafe.studycaferenewal.service.product.ProductService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final ProductService productService;

//    @GetMapping("/")
    public String homeForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("loginMember", loginMember);
        }
        return "home";
    }

    @GetMapping("/")
    public String homeLoginV5ArgumentResolver(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("loginMember", loginMember);
        }

        List<Board> boards = boardService.findBoards();
        List<BoardForm> boardForms = boardService.boardsToBoardForms(boards);
        model.addAttribute("boards", boardForms);

        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);

        return "home";
    }

    //view.render(model,request,response);
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();

//        if (result != 1) {
//            out.println("<script>alert('���!! �Է��Ͻ� ������ ��ġ���� �ʽ��ϴ�.');</script>");
//            out.flush();
//        }
}

//}



//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView Login(@RequestParam("id") String id, @RequestParam("password") String password,
//                              HttpSession session, HttpServletResponse response) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        Criteria boardCri = new Criteria();
//        Criteria noticeCri = new Criteria();
//
//        mav.addObject("boardList", boardService.getBoardList(boardCri));
//        mav.addObject("noticeList", noticeService.getNoticeList(noticeCri));
//
//        PageMaker boardPageMaker = new PageMaker();
//        PageMaker noticePageMaker = new PageMaker();
//
//        boardPageMaker.setCri(boardCri);
//        noticePageMaker.setCri(noticeCri);
//        boardPageMaker.setTotalCount(boardService.getBoardListCount());
//        noticePageMaker.setTotalCount(noticeService.getNoticeListCount());
//
//        mav.addObject("boardPageMaker", boardPageMaker);
//        mav.addObject("noticePageMaker", noticePageMaker);
//
//        MemberVO member = new MemberVO();
//        member.setUser_id(id);
//        member.setUser_password(password);
//
//        int result = memberService.checkMember(member, session);
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        if (result != 1) {
//            out.println("<script>alert('���!! �Է��Ͻ� ������ ��ġ���� �ʽ��ϴ�.');</script>");
//            out.flush();
//        }
//
//        mav.setViewName("common/LoginMain");
//
//        return mav;
//    }


//    @RequestMapping(value = "/LoginMain", method = RequestMethod.GET)
//    public ModelAndView LoginMain(@RequestParam(defaultValue = "null") String page,
//                                  @RequestParam(defaultValue = "null") String perPageNum, @RequestParam(defaultValue = "null") String kind)
//            throws Exception {
//        ModelAndView mav = new ModelAndView();
//        Criteria boardCri = new Criteria();
//        Criteria noticeCri = new Criteria();
//
//        if (kind.equals("board")) {
//            boardCri.setPage(Integer.parseInt(page));
//            boardCri.setPerPageNum(Integer.parseInt(perPageNum));
//        } else if (kind.equals("notice")) {
//            noticeCri.setPage(Integer.parseInt(page));
//            noticeCri.setPerPageNum(Integer.parseInt(perPageNum));
//        }
//
//        mav.addObject("boardList", boardService.getBoardList(boardCri));
//        mav.addObject("noticeList", noticeService.getNoticeList(noticeCri));
//
//        PageMaker boardPageMaker = new PageMaker();
//        PageMaker noticePageMaker = new PageMaker();
//
//        boardPageMaker.setCri(boardCri);
//        noticePageMaker.setCri(noticeCri);
//        boardPageMaker.setTotalCount(boardService.getBoardListCount());
//        noticePageMaker.setTotalCount(noticeService.getNoticeListCount());
//
//        mav.addObject("boardPageMaker", boardPageMaker);
//        mav.addObject("noticePageMaker", noticePageMaker);
//
//        mav.setViewName("common/LoginMain");
//
//        return mav;
//    }
//

