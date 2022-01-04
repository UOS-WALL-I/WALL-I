package NPC.walli.filter;

import NPC.walli.controller.SessionConst;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members", "/members/*", "/logout", "/css/*", "/favicon.ico", "/js/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            HttpSession session = httpRequest.getSession(false);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인으로 리다이렉트

                    //URI 에 파라미터 값 이어 붙이기 without '&'
                    requestURI += "?";
                    Enumeration<String> params = request.getParameterNames();
                    while (params.hasMoreElements()) {
                        String name = params.nextElement();
                        requestURI += name + "=" + URLEncoder.encode(request.getParameter(name), StandardCharsets.UTF_8);
                    }

                    httpResponse.sendRedirect("/?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * whiteList의 경우 인증 체크를 안하도록 한다.
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
