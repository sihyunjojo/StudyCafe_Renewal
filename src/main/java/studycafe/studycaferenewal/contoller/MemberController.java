package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String JoinForm(Model model) {
        model.addAttribute("member", new Member());
        return "/member/AddMemberForm";
    }

    @PostMapping("/new")
    public String Join(Member member, Model model){
        String userId = memberService.join(member);
        log.info(userId);
        if (userId == null) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            model.addAttribute("member", member);
            return "/member/AddMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/idquiry")
    public String findIdForm (){
        return "/member/FindIdForm";
    }

    @PostMapping("/idquiry")
    public String findId (Member member, Model model) throws IOException {
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
    public String findPassword(Member member, Model model) {
        Optional<Member> result = memberService.checkById(member);
        model.addAttribute("member", result);

        return "/member/FindPasswordResult";
    }

    @GetMapping("/info")
    public String InfoForm(Model model) {
        model.addAttribute("member", new Member());
        return "/member/MemberInfo";
    }

}
