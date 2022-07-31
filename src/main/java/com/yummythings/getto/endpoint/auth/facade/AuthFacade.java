package com.yummythings.getto.endpoint.auth.facade;

import com.yummythings.getto.common.component.CookieUtil;
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

import static com.yummythings.getto.common.constant.AccessTokenClaim.OAUTH_MEMBER_ID;
import static com.yummythings.getto.common.constant.AccessTokenClaim.OAUTH_ORGANIZATION;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthFacade {

    private final TokenProvider tokenProvider;
    private final JwtService jwtService;
    private final KakaoMemberInfoService kakaoMemberInfoService;
    private final CookieUtil cookieUtil;

    public LoginResponseDTO reissueToken(String reissueToken, String accessToken, HttpServletResponse response) {

        Optional<LoginToken> loginToken = jwtService.findByRefreshToken(reissueToken);
        if (tokenValidate(reissueToken, accessToken) && loginToken.isPresent()) {

            TokenDTO tokenDTO = updateRefreshTokenByAccessToken(accessToken, loginToken.get().getGettoMember().getIdx());

            response.setHeader( "Set-Cookie", cookieUtil.createRefreshTokenCookie(tokenDTO.getRefreshToken()));

            return getLoginResponseDTO(loginToken.get().getGettoMember().getIdx(), tokenDTO.getAccessToken());
        }

        throw new JwtException("토큰 재발급 실패");
    }

    private LoginResponseDTO getLoginResponseDTO(Long gettoIdx, String accessToken) {
        KakaoMemberInfo kaKaoMemberInfo = kakaoMemberInfoService.findKaKaoMemberInfo(gettoIdx);
        return LoginResponseDTO.builder().accessToken(accessToken)
                .thumbnailImageUrl(kaKaoMemberInfo.getThumbnailImageUrl())
                .nickname(kaKaoMemberInfo.getNickName()).build();
    }

    private TokenDTO updateRefreshTokenByAccessToken(String accessToken, Long gettoIdx) {
        Claims claims = tokenProvider.parseToken(accessToken);
        return jwtService.updateRefreshToken(gettoIdx, (String) claims.get(OAUTH_ORGANIZATION), (String) claims.get(OAUTH_MEMBER_ID));
    }

    private boolean tokenValidate(String reissueToken, String accessToken) {
        return tokenProvider.validateToken(reissueToken) &&
                tokenProvider.validateToken(accessToken);
    }

}
