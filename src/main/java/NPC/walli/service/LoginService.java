package NPC.walli.service;

import NPC.walli.domain.Member;
import NPC.walli.repository.MemberRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    PasswordEncoder passwordEncoder;

    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }
}
