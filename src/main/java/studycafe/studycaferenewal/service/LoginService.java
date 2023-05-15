package studycafe.studycaferenewal.service;

import studycafe.studycaferenewal.domain.Member;

public interface LoginService {
    public Member login(String loginId, String password);
}
