package com.yummythings.getto.service;

import com.yummythings.getto.common.component.TokenProvider;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
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
    public TokenDTO getJwtToken(KakaoAuthResponseDTO kakaoAuth, KakaoAuthInfoDTO authUserInfo) {
        return isRegisteredMember(authUserInfo.getId())
                ? reissueToken()
                : issueJwtToken(kakaoAuth, authUserInfo);
    }

    private Boolean isRegisteredMember(Long KakaoId) {
        return false;
    }

    private TokenDTO issueJwtToken(KakaoAuthResponseDTO kakaoAuth, KakaoAuthInfoDTO authUserInfo) {
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        User securityUser = new User(authUserInfo.getId().toString(), "", authorities );
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, kakaoAuth.getAccessToken(), authorities);
        return tokenProvider.createTokenByKakao(authenticationToken, kakaoAuth.getAccessToken(), kakaoAuth.getRefreshToken());
    }

    private TokenDTO reissueToken() {
        return new TokenDTO();

    }


}
