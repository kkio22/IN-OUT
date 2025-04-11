package com.example.allin.filter;

import com.example.allin.exception.ErrorCode;
import com.example.allin.exception.FriendUnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FriendFilter implements Filter {
    private static final List<String> excludeUrls = List.of("/users/user","/users/login");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        log.info("Request URI: {}", requestURI);

        if(excludeUrls.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 세션 검증: 로그인하지 않은 사용자는 403 Unauthorized 반환
        if (httpRequest.getSession(false) == null ||
                httpRequest.getSession(false).getAttribute("sessionResponseDto") == null) {

            log.warn("Unauthorized access attempt to {}", requestURI);
            throw new FriendUnauthorizedException(ErrorCode.UNAUTHORIZED);
        }

        // 세션이 유효하면 다음 필터로 전달
        chain.doFilter(request, response);
    }
}
