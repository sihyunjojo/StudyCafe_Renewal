package studycafe.studycaferenewal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userPassword;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String birth;

    public Member() {

    }

    public Member(String userId, String userPassword, String name, String gender, String phone, String address, String email, String birth) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.birth = birth;
    }

}
