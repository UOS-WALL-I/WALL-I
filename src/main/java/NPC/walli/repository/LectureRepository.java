package NPC.walli.repository;

import NPC.walli.domain.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LectureRepository extends MongoRepository<Lecture, Long> {
}
