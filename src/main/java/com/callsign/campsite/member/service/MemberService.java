package com.callsign.campsite.member.service;

import com.callsign.campsite.auth.dto.TokenInfo;


public interface MemberService {
    public TokenInfo login(String memberId, String password);
}
