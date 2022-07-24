package com.yummythings.getto.service;

import com.yummythings.getto.common.component.TokenProvider;
import com.yummythings.getto.domain.GettoMember;
import com.yummythings.getto.domain.LoginToken;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
    private final TokenProvider tokenProvider;
    private final JwtRepository jwtRepository;

    public TokenDTO issueJwtToken(Long gettoIdx, String oauthOrganization, String oauthMemberId) {

        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        User securityUser = new User(gettoIdx.toString(), "", authorities );
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, "", authorities);
        TokenDTO tokenDTO = tokenProvider.generateLoginToken(authenticationToken, oauthOrganization, oauthMemberId);

        jwtRepository.save(LoginToken.builder().refreshToken(tokenDTO.getRefreshToken()).gettoMember(GettoMember.builder().idx(gettoIdx).build()).created_at(LocalDateTime.now()).build());

        return tokenDTO;
    }

    public TokenDTO updateRefreshToken(Long gettoIdx, String oauthOrganization, String oauthMemberId) {
        jwtRepository.deleteLoginTokensByGettoMemberIdx(gettoIdx);
        return this.issueJwtToken(gettoIdx,oauthOrganization,oauthMemberId);
    }


}
