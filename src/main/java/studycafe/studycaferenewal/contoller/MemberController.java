package studycafe.studycaferenewal.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/new")
    public String JoinForm() {
        return "member/JoinForm";
    }


    @PostMapping("/new")
    public String JoinForm(HttpServletRequest request, HttpServletResponse response,Member member) throws Exception {
        memberService.join(member);
//        response.sendRedirect("/");
        return "redirect:/";
    }

    @GetMapping("/idquiry")
    public String findIdForm (){
        return "/member/FindIdForm";
    }

    @PostMapping("/idquiry")
    public String findIdForm (@RequestParam("user_name") String userName, @RequestParam("user_phone") String userPhone,HttpServletResponse response, Model model) throws IOException {
        Member member = new Member();
        member.setName(userName);
        member.setPhone(userPhone);

        Optional<Member> result = memberService.FindMemberByNameAndPhone(member);

        if (result.isPresent()){
            model.addAttribute("member", result.get());
            return "member/SuccessFindId";
        } else {
//            PrintWriter out = response.getWriter();
//            model.addAttribute("msg", "경고!! 헤당하는 회원이 없습니다!!");
//            out.flush();
            return "member/FailFindId";
        }

    }

    @GetMapping("/pwquiry")
    public String findPasswordForm (){
        return "/member/FindPasswordForm";
    }

    @PostMapping("/pwquiry")
    public String findPasswordForm(@RequestParam("user_id") String usesrId, Model model) {
        Member member = new Member();
        member.setId(usesrId);

        Optional<Member> result = memberService.checkById(member);
        System.out.println("result = " + result);

        if (result.isPresent()) {
            model.addAttribute("member", result.get());
            return "member/SuccessFindPassword";
        } else {
            return "member/FailFindPassword";
        }
    }






}
