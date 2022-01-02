package NPC.walli.repository;

import NPC.walli.domain.PDF;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFRepository extends MongoRepository<PDF, String> {

}
