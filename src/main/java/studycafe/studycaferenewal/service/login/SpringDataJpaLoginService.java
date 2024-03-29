package studycafe.studycaferenewal.service.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.JpaMemberRepository;
import studycafe.studycaferenewal.service.login.LoginService;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpringDataJpaLoginService implements LoginService {
    private final JpaMemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findFirstByUserLoginId(loginId)
                .filter(m -> m.getUserPassword().equals(password))
                .orElse(null);

    }
}

