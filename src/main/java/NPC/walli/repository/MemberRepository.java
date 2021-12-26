package NPC.walli.repository;

import NPC.walli.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {
    List<Member> findByName(String name);

    //저장소에 Id가 없을수도 있으니 Optional사용
    public default Optional<Member> findByLoginId(String loginId) {
        return this.findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }
}
