package com.yummythings.getto.service;

import com.yummythings.getto.component.TokenProvider;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
    private final TokenProvider tokenProvider;
    public TokenDTO getJwtToken(KakaoAuthInfoDTO authUserInfo) {
        return isRegisteredMember(authUserInfo.getId()) ? reissueToken() : issueToken();
    }

    private Boolean isRegisteredMember(Long KakaoId) {


        return true;
    }

    private TokenDTO issueToken() {

//        tokenProvider.createToken()
        // 토큰 + 사용자 정보 저장
        return new TokenDTO();

    }

    private TokenDTO reissueToken() {
        return new TokenDTO();

    }


}
