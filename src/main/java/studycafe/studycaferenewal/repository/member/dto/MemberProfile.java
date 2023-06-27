package studycafe.studycaferenewal.repository.member.dto;

import lombok.Getter;
import lombok.Setter;
import studycafe.studycaferenewal.domain.Member;

@Getter
@Setter
public class MemberProfile {
    private String name;
    private String email;
    private String provider;
    private String nickname;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .build();
    }

}