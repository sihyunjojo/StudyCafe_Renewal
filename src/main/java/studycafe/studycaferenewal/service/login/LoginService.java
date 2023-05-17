package studycafe.studycaferenewal.service.login;

import studycafe.studycaferenewal.domain.Member;

public interface LoginService {
    public Member login(String loginId, String password);
}
