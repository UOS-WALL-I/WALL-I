package NPC.walli.repository;

import NPC.walli.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, Long> {
    public default Member findOne(String name) {
        List<Member> members = this.findAll();

        for (Member m : members) {
            if (m.getName().compareTo(name) == 0)
                return m;
        }
        return null;
    }
}
