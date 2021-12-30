package NPC.walli.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class MemberForm {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp="[a-zA-Z1-9]{8,20}", message = "비밀번호는 알파벳 대소문자와 숫자를 포함한 8~20자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 다시 입력해주세요.")
    private String repeatPassword;

    private String phoneNumber;
}
