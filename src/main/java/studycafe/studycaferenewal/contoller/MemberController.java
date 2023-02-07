package studycafe.studycaferenewal.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member/join")
    public String JoinForm() {
        return "member/joinForm";
    }


    @PostMapping("join")
    public String JoinForm(Member member) throws Exception {
        memberService.join(member);
        return "redirect:/";
    }

}
