package studycafe.studycaferenewal.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import studycafe.studycaferenewal.SessionConst;
import studycafe.studycaferenewal.argumentresolver.Login;
import studycafe.studycaferenewal.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static studycafe.studycaferenewal.SessionConst.LOGIN_MEMBER;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session != null) {
            Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);
            log.info("loginmember={}", loginMember);
            // 모델에 세션 값을 추가합니다
            request.setAttribute("loginMember", loginMember);
            log.info("request={}", request);


        }
        return true;
    }
}
