package studycafe.studycaferenewal.contoller;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.lang.annotation.Retention;

@Sl4
@Controller
@RequiredArgsConstructor
public class LogionController {
    private final LoginService loginService;

    @PostMapping("/login")
    public String loginForm(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword())
    }
}
