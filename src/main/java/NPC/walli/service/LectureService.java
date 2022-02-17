package NPC.walli.service;

import NPC.walli.domain.Lecture;
import NPC.walli.repository.LectureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    /**
     * 교수 관련 기능
     */
    // subject 별 Lecture 전체 조회
    public List<Lecture> findBySubject(String subject) {
        return lectureRepository.findAllBySubject(subject);
    }
}
