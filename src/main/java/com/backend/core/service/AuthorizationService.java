package com.backend.core.service;

import com.backend.core.concert.Concert;
import com.backend.core.member.Member;

public class AuthorizationService {
    public static boolean canUpdateConcert(Member member, Concert concert){
        return member.getId().equals(concert.getPerformer().getId());
    }
}
