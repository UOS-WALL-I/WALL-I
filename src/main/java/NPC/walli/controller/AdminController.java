package NPC.walli.controller;

import NPC.walli.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/admin-home";
    }
}
