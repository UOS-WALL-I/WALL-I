package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.repository.MemberRepository;
import NPC.walli.service.LoginService;
import NPC.walli.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        log.info("login Form");
        model.addAttribute("loginForm", new LoginForm());
        return "members/signIn";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/signIn";
        }
        log.info(loginForm.getLoginId().toString());

//        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "members/signIn";
//        }
        return "redirect:/";
    }
}
