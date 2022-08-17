package com.yummythings.getto.endpoint.auth.facade;

import com.yummythings.getto.common.component.TokenProvider;
import com.yummythings.getto.domain.KakaoMemberInfo;
import com.yummythings.getto.domain.LoginToken;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.endpoint.oauth.response.LoginResponseDTO;
import com.yummythings.getto.service.JwtService;
import com.yummythings.getto.service.KakaoMemberInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.yummythings.getto.common.constant.RefreshTokenClaim.OAUTH_ORGANIZATION;
import static com.yummythings.getto.common.constant.RefreshTokenClaim.SUBJECT;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthFacade {

    private final TokenProvider tokenProvider;
    private final JwtService jwtService;
    private final KakaoMemberInfoService kakaoMemberInfoService;

    public LoginResponseDTO reissueToken(String reissueToken, String accessToken, HttpServletResponse response) {
        if (reissueToken == null || reissueToken.isBlank()) {
            throw new JwtException("refresh token  없음");
        }

        Optional<LoginToken> loginToken = jwtService.findByRefreshToken(reissueToken);
        if (tokenValidate(reissueToken, accessToken) && loginToken.isPresent()) {

            TokenDTO tokenDTO = updateRefreshTokenByAccessToken(reissueToken, loginToken.get().getGettoMember().getIdx());
            return getLoginResponseDTO(loginToken.get().getGettoMember().getIdx(), tokenDTO.getAccessToken(), tokenDTO.getRefreshToken());
        }

        throw new JwtException("토큰 재발급 실패");
    }

    private LoginResponseDTO getLoginResponseDTO(Long gettoIdx, String accessToken, String refreshToken) {
        KakaoMemberInfo kaKaoMemberInfo = kakaoMemberInfoService.findKaKaoMemberInfo(gettoIdx);
        return LoginResponseDTO.builder().accessToken(accessToken)
                .refreshToken(refreshToken)
                .thumbnailImageUrl(kaKaoMemberInfo.getThumbnailImageUrl())
                .nickname(kaKaoMemberInfo.getNickName()).build();
    }

    private TokenDTO updateRefreshTokenByAccessToken(String reissueToken, Long gettoIdx) {
        Claims claims = tokenProvider.parseToken(reissueToken);
        return jwtService.updateRefreshToken(gettoIdx, (String) claims.get(OAUTH_ORGANIZATION), (String) claims.get(SUBJECT));
    }

    private boolean tokenValidate(String reissueToken, String accessToken) {
        return tokenProvider.validateToken(reissueToken);
//                && tokenProvider.validateToken(accessToken);
    }

}
