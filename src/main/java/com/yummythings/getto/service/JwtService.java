package com.yummythings.getto.service;

import com.yummythings.getto.common.component.TokenProvider;
import com.yummythings.getto.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
    private final TokenProvider tokenProvider;

    public TokenDTO issueJwtToken(Long gettoIdx, String oauthOrganization, String oauthMemberId) {


//        jwtRepository.save(LoginToken.builder().refreshToken("").memberIdx(member.getIdx()).build());


        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        User securityUser = new User(gettoIdx.toString(), "", authorities );
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, "", authorities);


        return tokenProvider.generateLoginToken(authenticationToken, oauthOrganization, oauthMemberId);
    }

    public TokenDTO reissueToken(Long gettoIdx, String oauthOrganization, String oauthMemberId) {
        return this.issueJwtToken(gettoIdx,oauthOrganization,oauthMemberId);
    }


}
