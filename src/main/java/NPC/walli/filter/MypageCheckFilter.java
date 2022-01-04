package NPC.walli.filter;

import NPC.walli.controller.SessionConst;
import NPC.walli.domain.Member;
import NPC.walli.domain.Role;
import lombok.extern.slf4j.Slf4j;

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
public class MypageCheckFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            HttpSession session = httpRequest.getSession(false);

            if (session.getAttribute(SessionConst.UPDATE_MEMBER) == null) {
                httpResponse.sendRedirect("/home");
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("password 체크 완료");
        }
    }

}
