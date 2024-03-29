package studycafe.studycaferenewal.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studycafe.studycaferenewal.domain.Member;
import studycafe.studycaferenewal.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class JpaLoginService implements LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByUserId(loginId)
                .filter(m -> m.getUserPassword().equals(password))
                .orElse(null);
    }
}

