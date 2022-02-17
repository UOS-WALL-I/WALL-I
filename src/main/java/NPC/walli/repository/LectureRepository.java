package NPC.walli.repository;

import NPC.walli.domain.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LectureRepository extends MongoRepository<Lecture, Long> {
    List<Lecture> findAllBySubject(String subject);
}
