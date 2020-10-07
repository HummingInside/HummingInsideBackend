package com.backend.application.service;

import com.backend.application.dto.member.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService extends UserDetailsService {

    public Long joinUser(MemberDto memberDto);

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException;
}
