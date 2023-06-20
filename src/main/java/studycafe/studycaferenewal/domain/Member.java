package studycafe.studycaferenewal.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String phone;

    @Email
    private String email;

    private String address;
    private String birth;
    private String provider;
    private String nickname;


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
