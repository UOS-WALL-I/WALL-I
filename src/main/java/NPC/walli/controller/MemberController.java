package NPC.walli.controller;

import NPC.walli.domain.Member;
import NPC.walli.domain.Subject;
import NPC.walli.repository.MemberRepository;
import NPC.walli.repository.SubjectRepository;
import NPC.walli.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/members/new")
    public Member create(@RequestParam("name") String name) {
        Member member = new Member();

        member.setId(sequenceGeneratorService.getSequenceNumber(Member.SEQUENCE_NAME));
        member.setName(name);

        memberRepository.save(member);

        return member;
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
