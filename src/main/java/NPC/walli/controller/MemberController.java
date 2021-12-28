package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.service.MemberService;
import NPC.walli.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@AllArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    private final SequenceGeneratorService sequenceGeneratorService;

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

        member.setId(sequenceGeneratorService.getSequenceNumber(Member.SEQUENCE_NAME));
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setRepeatPassword(form.getRepeatPassword());
        member.setEmail(form.getEmail());
        member.setPhoneNumber(form.getPhoneNumber());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> findByName(@RequestParam("name") String name) {
        return memberService.findByName(name);
    }

}
