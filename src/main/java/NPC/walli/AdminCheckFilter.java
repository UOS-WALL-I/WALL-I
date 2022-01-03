package NPC.walli;

import NPC.walli.domain.Member;
import NPC.walli.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
public class AdminCheckFilter implements Filter {

//    private static final String[] blacklist = {"/admin", "/home"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            HttpSession session = httpRequest.getSession(false);
//            if (isAdminCheckPath(requestURI)) {
//
//            }
            Enumeration<String> enumeration = session.getAttributeNames();
            Member member = (Member) session.getAttribute(enumeration.nextElement() + "");

            if (member.getRole() != Role.Admin) {
                httpResponse.sendRedirect("/home");
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("Admin 체크 완료");
        }
    }

    /**
     * blacklist 의 경우만 admin 인증 체크를 진행한다.
     */
//    private boolean isAdminCheckPath(String requestURI) {
//        return PatternMatchUtils.simpleMatch(blacklist, requestURI);
//    }
}
