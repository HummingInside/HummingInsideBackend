package com.backend;

import com.backend.core.member.Member;
import com.backend.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class WithUserSecurityContextFactory implements
        WithSecurityContextFactory<WithUser> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public SecurityContext createSecurityContext(WithUser withUser) {
        UserDetails principal = createUser(withUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

    private Member createUser(WithUser withUser){
        Member member = Member.builder()
                .name(withUser.name())
                .email(withUser.email())
                .password("aaa")
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return memberRepository.save(member);
    }
}
