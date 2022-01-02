package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.service.MailService;
import NPC.walli.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    private final MailService mailService;

    PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/signUp";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/signUp";
        }
        Member member = new Member();

        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setPhoneNumber(form.getPhoneNumber());

        memberService.join(member);
        return "redirect:/";
    }

    @PostMapping("/members/emailCheck")
    @ResponseBody
    public String memberEmailCheck(String email) throws Exception {
        Member member = memberService.findByEmail(email);
        if (member == null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/members/codeCheck")
    @ResponseBody
    public String emailCodeCheck(String email) throws Exception {
        String code = mailService.createKey();
        mailService.sendMessage(email, code);
        return code;
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> findByName(@RequestParam("name") String name) {
        return memberService.findByName(name);
    }

}
