package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String Join(Member member, Model model) {
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
    public String findIdForm() {
        return "/member/FindIdForm";
    }

    @PostMapping("/idquiry")
    public String findUserId(Member member, Model model) throws IOException {
        Optional<Member> result = memberService.findMemberByNameAndPhone(member);
        model.addAttribute("member", result);

        return "/member/FindIdResult";
//            PrintWriter out = response.getWriter();
//            model.addAttribute("msg", "경고!! 헤당하는 회원이 없습니다!!");
//            out.flush();
    }

    @GetMapping("/pwquiry")
    public String findPasswordForm() {
        return "/member/FindPasswordForm";
    }

    @PostMapping("/pwquiry")
    public String findPassword(Member member, Model model) {
        Optional<Member> result = memberService.findById(member);
        model.addAttribute("member", result);

        return "/member/FindPasswordResult";
    }

    @GetMapping("/edit")
    public String EditForm(@Login Member member, Model model) {
        if (member == null) {
            return "redirect:/"; //이런 코드 각각 넣어줘야하나??
        }
        model.addAttribute("loginMember", member);
        return "/member/EditMemberForm";
    }

    @PostMapping("/edit")
    public String Edit(Member updateMember, HttpServletRequest request) {
        log.info("updateMember = {}", updateMember);
        memberService.update(updateMember);

        Member member = memberService.findById(updateMember).orElseThrow();

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", member);

        log.info("교체 성공 session member ={}", session.getAttribute("loginMember"));

        return "redirect:/member/info";
    }

    @PostMapping("/checkPw")
    public String CheckPw(@Login Member member, @ModelAttribute MemberUpdateForm form, Model model) {
        if (member == null) {
            return "redirect:/"; //이런 코드 각각 넣어줘야하나??
        }
        model.addAttribute("loginMember", member);

        log.info("same = {}", form.getCheckPassword().equals(form.getUserPassword()));
        log.info("{},{}", form.getCheckPassword().getClass(), form.getUserPassword().getClass());

        if (form.getCheckPassword().equals(form.getUserPassword())) {
            model.addAttribute("same_password", "비밀번호 일치");
            model.addAttribute("updateMember", form);
        }
        else{
            model.addAttribute("different_password", "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        }

        return "/member/EditMemberForm";
    }
    @GetMapping("/info")
    public String InfoForm(@Login Member member, Model model) {

        log.info("member = {}", member);

        if (member == null) {
            return "redirect:/"; //이런 코드 각각 넣어줘야하나??
        }
        model.addAttribute("loginMember", member);
        return "/member/MemberInfo";
    }


}
