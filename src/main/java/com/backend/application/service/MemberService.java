package com.backend.application.service;

import com.backend.application.dto.member.MemberCreateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
