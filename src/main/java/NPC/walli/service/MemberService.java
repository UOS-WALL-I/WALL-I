package NPC.walli.service;

import NPC.walli.domain.Member;
import NPC.walli.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 & 수정
     */
    @Transactional
    public ObjectId join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 조회
     */
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void delete(ObjectId objectId) {
        memberRepository.deleteById(objectId);
    }
}
