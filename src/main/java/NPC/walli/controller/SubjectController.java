package NPC.walli.controller;

import NPC.walli.domain.Subject;
import NPC.walli.repository.SubjectRepository;
import NPC.walli.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/subjects/new")
    public Subject create(@RequestParam("name") String name) {
        Subject subject = new Subject();

        subject.setId(sequenceGeneratorService.getSequenceNumber(Subject.SEQUENCE_NAME));
        subject.setName(name);

        subjectRepository.save(subject);

        return subject;
    }

    @GetMapping("/subjects")
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
