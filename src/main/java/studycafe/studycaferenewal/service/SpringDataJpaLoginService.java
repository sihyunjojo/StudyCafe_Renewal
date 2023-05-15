package studycafe.studycaferenewal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.JpaMemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpringDataJpaLoginService implements LoginService{
    private final JpaMemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findFirstByUserId(loginId)
                .filter(m -> m.getUserPassword().equals(password))
                .orElse(null);

    }
}

