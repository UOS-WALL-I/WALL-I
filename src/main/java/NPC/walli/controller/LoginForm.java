package NPC.walli.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String redirectURL;
}
