package studycafe.studycaferenewal.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String userLoginId;
    @NotEmpty
    private String userPassword;
    @NotEmpty
    private String name;
    @NotEmpty
    private String gender;
    @NotNull
    private String phone;

    @Email
    private String email;

    private String address;
    private String birth;
    private String provider;
    private String nickname;


    @Builder //생성을 Builder 패턴으로 하기 위해서
    public Member(Long id, String name, String email, String provider, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userLoginId='" + userLoginId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birth='" + birth + '\'' +
                ", provider='" + provider + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
