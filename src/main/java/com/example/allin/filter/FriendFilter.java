package com.example.allin.filter;

import com.example.allin.exception.ErrorCode;
import com.example.allin.exception.FriendCustomException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class FriendFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        log.info("Request URI: {}", requestURI);

        // 세션 검증: 로그인하지 않은 사용자는 401 Unauthorized 반환
        if (httpRequest.getSession(false) == null ||
                httpRequest.getSession(false).getAttribute("userId") == null) {

            log.warn("Unauthorized access attempt to {}", requestURI);
            throw new FriendCustomException(ErrorCode.UNAUTHORIZED);
        }

        // 세션이 유효하면 다음 필터로 전달
        chain.doFilter(request, response);
    }
}
