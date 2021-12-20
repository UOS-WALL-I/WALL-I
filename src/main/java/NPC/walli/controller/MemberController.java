package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.domain.Subject;
import NPC.walli.repository.MemberRepository;
import NPC.walli.repository.SubjectRepository;
import NPC.walli.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

//    @GetMapping("/members/new")
//    public Member create(@RequestParam("name") String name) {
//        Member member = new Member();
//
//        member.setId(sequenceGeneratorService.getSequenceNumber(Member.SEQUENCE_NAME));
//        member.setName(name);
//
//        memberRepository.save(member);
//
//        return member;
//    }

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

        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/subject")
    public String addSubject(@RequestParam("name") String name, @RequestParam("subject") String subject) {
        Member member = memberRepository.findOne(name);
        member.getSubjectList().add(subject);

        memberRepository.save(member);

        return "redirect:/";
    }

    @GetMapping("/members/subjects")
    public List<Subject> findSubjectAll(@RequestParam("name") String name) {
        Member member = memberRepository.findOne(name);
        List<Subject> subjects = new ArrayList<>();
        List<String> memberSubjects = member.getSubjectList();

        for (String s : memberSubjects) {
            subjects.add(subjectRepository.findOne(s));
        }

        return subjects;
    }

}
