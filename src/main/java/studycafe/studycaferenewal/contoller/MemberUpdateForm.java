package studycafe.studycaferenewal.contoller;

import lombok.Data;

@Data
public class MemberUpdateForm {

    private String userPassword;
    private String checkPassword;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String birth;

    public MemberUpdateForm() {

    }

    public MemberUpdateForm(String userPassword,String checkPassword, String name, String gender, String phone, String address, String email, String birth) {
        this.userPassword = userPassword;
        this.checkPassword = checkPassword;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.birth = birth;
    }
}
