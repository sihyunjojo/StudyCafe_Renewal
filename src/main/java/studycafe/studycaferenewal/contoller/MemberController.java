package studycafe.studycaferenewal.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member/new")
    public String JoinForm() {
        return "member/JoinForm";
    }


    @PostMapping("member/new")
    public String JoinForm(HttpServletRequest request, HttpServletResponse response,Member member) throws Exception {
        memberService.join(member);
//        response.sendRedirect("/");
        return "redirect:/";
    }

    @GetMapping("/member/idquiry")
    public String findIdForm (){
        return "/member/FindIdForm";
    }

    @PostMapping("/member/idquiry")
    public void findIdForm (HttpServletRequest request,HttpServletResponse response, ModelAndView mav) throws IOException {
        Member member = new Member();

        member.setName(request.getParameter("user_name"));
        member.setPhone(request.getParameter("user_phone"));

        System.out.println("member = " + member.getName() + member.getPhone());

        Optional<Member> result = memberService.FindMemberByNameAndPhone(member);
        System.out.println("result = " + result);
        mav.addObject("member", result);

        if (result != null){
            response.sendRedirect("/member/idquiryresult");
            System.out.println("response = " + response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('경고!! 헤당하는 회원이 없습니다!!');</scrpt>");
            out.flush();
            response.sendRedirect("/");
        }

    }

    @GetMapping("/member/idquiryresult")
    public String SuccessFindId(HttpServletRequest request, HttpServletResponse response, Model model){
        return "/member/SuccessFindId";
    }

    @GetMapping("/member/pwquiryresult")
    public String SuccessFindPassword(){
        return "/member/SuccessFindPassword";
    }

    @GetMapping("/member/pwquiry")
    public String findPasswordForm (){
        return "/member/FindPasswordForm";
    }

    @PostMapping("/member/pwquiry")
    public void findPasswordForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        Member member = new Member();
    }






}
