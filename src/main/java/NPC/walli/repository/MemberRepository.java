package NPC.walli.repository;

import NPC.walli.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {
    public default Member findOne(String name) {
            List<Member> members = this.findAll();

            for (Member m : members) {
                if (m.getName().compareTo(name) == 0)
                    return m;
            }
            return null;
    }

//    public default Member findByLoginId(String loginId) {
//        List<Member> members = this.findAll();
//        for (Member m : members) {
//            if (m.getLoginId().equals(loginId)) {
//                return m;
//            }
//        }
//        return null;
//    }

    //저장소에 Id가 없을수도 있으니 Optional사용
    public default Optional<Member> findByLoginId(String loginId) {
        return this.findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }
}
