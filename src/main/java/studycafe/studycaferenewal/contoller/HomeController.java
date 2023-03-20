package studycafe.studycaferenewal.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/")
    public String homeForm(Member member, HttpSession session) throws Exception {
        memberService.checkMember(member, session);
        //view.render(model,request,response);
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();

//        if (result != 1) {
//            out.println("<script>alert('���!! �Է��Ͻ� ������ ��ġ���� �ʽ��ϴ�.');</script>");
//            out.flush();
//        }

        return "home";
    }

}



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
//
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

