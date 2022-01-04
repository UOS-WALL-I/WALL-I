package NPC.walli.repository;

import NPC.walli.domain.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {

    List<Member> findByName(String name);

    //저장소에 Id가 없을수도 있으니 Optional사용
    Optional<Member> findByEmail(String email);
}
