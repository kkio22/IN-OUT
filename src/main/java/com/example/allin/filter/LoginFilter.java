package com.example.allin.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/*
 * filter 클래스의 역할:
 * 컨트롤러로 도달하기 전에 필터링 하기 위한 객체
  http 메시지가 왔을 때 가장 먼저 로그인 인증을 위해 만든 클래스
  1. 세션 생성하고, ID 저장
  2. 이후에 들어올 때 유효성 검사
 */
@Component
public class LoginFilter implements Filter {

    /*
    whiteList
    List.of : 불변 리스트 생성 메서드
    상수의 변수명은 모두 대문자로 + 언더바 -> 관례
     */
    private static final String[] EXCLUDED_URLS = {"/users/login", "users/user"};
//    List.of("/users/login", "users/user")

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            /*
            IOException : 입출력(I/O) 예외 (파일이 없거나, 읽을 수 없거나, 네트워크 끈긴 경우 등)
            ServletException : 서블릿이 잘못 동작할 때 발생
             */
            throws IOException, ServletException {

                // 다운캐스팅
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

                /*
                URI : 예) www.naver.com/sports -> /sports만 주는 것
                URL : 예시에서 다 주는 것
                비교군을 가져와서 로그인을 할지 말지 판별
                 */
                String requestURI = httpServletRequest.getRequestURI();

                /*
                로그인 했는지 검증하기
                 */
                if (isNotExcludedUrls(requestURI)) {

                    // 세션 생성
                    HttpSession session = httpServletRequest.getSession(false);

                    // "" 안에 있으면 스프링이 읽는다 -> "userId"는 user클래스에서 id 값을 찾아온다
                    if (session==null || session.getAttribute("userId")==null) {

                        throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "로그인 먼저 해주세요.");
                    }
                }

//                // 롣그인 안 된 상태일 경우 예외 던지고 종료
//                if(object==null) {
//                    throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "로그인 먼저 해주세요.");
//                }

                /*
                필터를 통과해서 간다
                다음 필터가 있으면 다음 필터로 넘겨주고, 없으면 이 필터가 마지막 필터가 되고 컨트롤러를 호출한다
                 */
                filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isNotExcludedUrls (String requestURI) {
                /*
                PatternMatchUtils : 배열 안의 값들을 하나씩 대조해보기
                있으면 true, 없으면 false 인데 앞에 ! 붙여서 반대로 없으면 true, 있으면 false -> 있으면 실행 X
                 */
                return !PatternMatchUtils.simpleMatch(EXCLUDED_URLS, requestURI);
    }



}
