package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.domain.Role;
import NPC.walli.service.MailService;
import NPC.walli.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Enumeration;
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
        member.setRole(Role.Student);
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

    @PostMapping("/update/passwordCheck")
    @ResponseBody
    public String memberPasswordCheck(HttpServletRequest request, String password) throws Exception {
        HttpSession session = request.getSession(false);
        Enumeration<String> enumeration = session.getAttributeNames();
        Member member = (Member) session.getAttribute(enumeration.nextElement() + "");

        if (passwordEncoder.matches(password, member.getPassword())) {
            session.setAttribute(SessionConst.UPDATE_MEMBER, member);
            return "success";
        } else {
            return "fail";
        }
    }

//    @GetMapping("/update")
//    public String updateForm(Model model) {
//        model.addAttribute("memberForm", new MemberForm());
//        return "/members/myPage";
//    }

    @GetMapping("/update")
    public String updateForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Enumeration<String> enumeration = session.getAttributeNames();
        Member member = (Member) session.getAttribute(enumeration.nextElement() + "");

        MemberForm memberForm = new MemberForm();

        memberForm.setName(member.getName());
        memberForm.setPhoneNumber(member.getPhoneNumber());

        model.addAttribute("memberForm", memberForm);
        return "/members/myPage";
    }

    @PostMapping("/update")
    public String update(MemberForm form, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            result.reject("update fail","something wrong");
            return "/members/myPage";
        }

        HttpSession session = request.getSession(false);
        Enumeration<String> enumeration = session.getAttributeNames();
        Member member = (Member) session.getAttribute(enumeration.nextElement() + "");

        member.setName(form.getName());
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setPhoneNumber(form.getPhoneNumber());
        session.removeAttribute(SessionConst.UPDATE_MEMBER);

        memberService.join(member);
        return "redirect:/home";
    }
}
