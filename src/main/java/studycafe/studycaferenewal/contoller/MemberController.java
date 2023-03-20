package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String JoinForm() {
        return "member/JoinForm";
    }


    @PostMapping("/new")
    public String JoinForm(Member member) throws Exception {
        memberService.join(member);
//        response.sendRedirect("/");
        return "redirect:/";
    }

    @GetMapping("/idquiry")
    public String findIdForm (){
        return "/member/FindIdForm";
    }

    @PostMapping("/idquiry")
    public String findIdForm (Member member, Model model) throws IOException {
        Optional<Member> result = memberService.FindMemberByNameAndPhone(member);
        model.addAttribute("member", result);

        return "/member/FindIdResult";
//            PrintWriter out = response.getWriter();
//            model.addAttribute("msg", "경고!! 헤당하는 회원이 없습니다!!");
//            out.flush();
    }

    @GetMapping("/pwquiry")
    public String findPasswordForm (){
        return "/member/FindPasswordForm";
    }

    @PostMapping("/pwquiry")
    public String findPasswordForm(Member member, Model model) {
        Optional<Member> result = memberService.checkById(member);
        model.addAttribute("member", result);

        return "/member/FindPasswordResult";
    }






}
