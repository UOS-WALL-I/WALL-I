package NPC.walli.repository;

import NPC.walli.domain.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<Subject, Long> {
    public default Subject findOne(String name) {
        List<Subject> subjects = this.findAll();

        for (Subject s : subjects) {
            if (s.getName().compareTo(name) == 0)
                return s;
        }
        return null;
    }
}
