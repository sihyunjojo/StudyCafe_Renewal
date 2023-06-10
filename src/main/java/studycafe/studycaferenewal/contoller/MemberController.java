package studycafe.studycaferenewal.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.dto.UpdateMemberDto;
import studycafe.studycaferenewal.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String JoinForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/addMemberForm";
    }

    @PostMapping("/new")
    public String Join(Member member, Model model) {
        String userId = memberService.join(member);
        log.info(userId);
        if (userId == null) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
//            model.addAttribute("member", member);
            return "member/addMemberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/idquiry")
    public String findIdForm() {
        return "/member/findIdForm";
    }

    @PostMapping("/idquiry")
    public String findUserId(Member member, Model model) {
        Optional<Member> result = memberService.findMemberByNameAndPhone(member);
        model.addAttribute("member", result);

        return "member/findIdResult";
        // 예외처리할때, 일치하는 회원 없으면 없다고 보여주기
    }

    @GetMapping("/pwquiry")
    public String findPasswordForm() {
        return "/member/findPasswordForm";
    }

    @PostMapping("/pwquiry")
    public String findPassword(Member member, Model model) {
        Optional<Member> result = memberService.findByUserId(member);
        model.addAttribute("member", result);

        return "member/findPasswordResult";
    }

    @GetMapping("/edit")
    public String EditForm(@Login Member member, Model model) {
//        if (member == null) {
//            return "redirect:/"; //이런 코드 각각 넣어줘야하나??
//        }
        model.addAttribute(LOGIN_MEMBER, member);
        return "member/editMemberForm";
    }

    @PostMapping("/edit")
    public String Edit(@Login Member loginmember, UpdateMemberDto updateMember, HttpServletRequest request) {
        log.info("updateMember = {}", updateMember);
        Member loginMember = memberService.findById(loginmember).orElseThrow();
        Member updatedMember = memberService.update(loginMember, updateMember).orElseThrow();

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, updatedMember);

        log.info("교체 성공 session member ={}", session.getAttribute(LOGIN_MEMBER));

        return "redirect:/member/info";
    }

    @PostMapping("/checkPw")
    public String CheckPw(@Login Member member, @ModelAttribute UpdateMemberDto form, Model model) {
//        if (member == null) {
//            return "redirect:/"; //이런 코드 각각 넣어줘야하나??
//        }
        model.addAttribute("loginMember", member);

        log.info("same = {}", form.getCheckPassword().equals(form.getUserPassword()));
        log.info("{},{}", form.getCheckPassword().getClass(), form.getUserPassword().getClass());

        if (form.getCheckPassword().equals(form.getUserPassword())) {
            model.addAttribute("same_password", "비밀번호 일치");
            model.addAttribute("updateMember", form);
        } else {
            model.addAttribute("different_password", "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        }

        return "member/editMemberForm";
    }

    @GetMapping("/info")
    public String InfoForm(@Login Member member, Model model) {
        if (member == null) {
            return "redirect:/login?redirectURL=/member/info/";
        }

        model.addAttribute(LOGIN_MEMBER, member);
        return "member/memberInfo";
    }


}
