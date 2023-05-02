package com.callsign.campsite.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 스프링 시큐리티 관련 유틸리티
 */
public class SecurityUtil {

    /**
     * 어떤 사용자가 API를 요청했는지 조회
     *
     * @return String memberId
     */
    public static String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
    }
}
