package NPC.walli.controller;

import NPC.walli.domain.Lecture;
import NPC.walli.domain.Member;
import NPC.walli.service.LectureService;
import NPC.walli.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class ProfessorController {

    private final LectureService lectureService;
    private final MemberService memberService;

    @GetMapping("/professor")
    public String professorHome(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Enumeration<String> names = session.getAttributeNames();
        Member professor = (Member) session.getAttribute(names.nextElement() + "");

        model.addAttribute("professor", professor);

        return "professor/professor-home";
    }

    @PostMapping("/professor")
    public String profHome(Model model, String subject) {
        List<Lecture> lectureList = lectureService.findBySubject(subject);

        log.info(subject);
        for (Lecture lecture : lectureList)
            log.info(lecture.getTitle());

        model.addAttribute("lectures", lectureList);

        return "professor/professor-home :: #resultDiv";
    }
}
