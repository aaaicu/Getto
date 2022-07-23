package com.yummythings.getto.endpoint.oauth.facade;

import com.yummythings.getto.domain.GettoMember;
import com.yummythings.getto.domain.KakaoMemberInfo;
import com.yummythings.getto.dto.LoginDTO;
import com.yummythings.getto.dto.TokenDTO;
import com.yummythings.getto.service.GettoMemberService;
import com.yummythings.getto.service.JwtService;
import com.yummythings.getto.service.KakaoMemberInfoService;
import com.yummythings.getto.service.UserAuthService;
import com.yummythings.getto.service.dto.KakaoAuthInfoDTO;
import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthFacade {
    private final JwtService jwtService;
    private final GettoMemberService gettoMemberService;
    private final UserAuthService userAuthService;
    private final KakaoMemberInfoService kakaoMemberInfoService;


    public TokenDTO getGettoLoginToken(KakaoAuthResponseDTO kakaoAuth) {
        KakaoAuthInfoDTO authUserInfo = this.userAuthService.getAuthUserInfo(kakaoAuth.getAccessToken());

        return gettoMemberService.isRegisteredMember(KakaoAuthInfoDTO.extract(authUserInfo).getKakaoId())
                ? reissue(authUserInfo)
                : signUp(authUserInfo);
    }

    private TokenDTO reissue(KakaoAuthInfoDTO authInfoDTO) {
        KakaoMemberInfo kaKaoMemberInfo = kakaoMemberInfoService.findKaKaoMemberInfo(authInfoDTO.getId());
        GettoMember gettoMember = gettoMemberService.findGettoMember("KAKAO", kaKaoMemberInfo.getIdx());


        return jwtService.reissueToken(gettoMember.getIdx(),"KAKAO", kaKaoMemberInfo.getKakaoId());
    }

    private TokenDTO signUp(KakaoAuthInfoDTO authInfoDTO) {

        KakaoMemberInfo kakaoMemberInfo = kakaoMemberInfoService.saveKakaoMember(KakaoAuthInfoDTO.extract(authInfoDTO));
        GettoMember gettoMember = createGettoMember(kakaoMemberInfo);

        GettoMember member = gettoMemberService.saveMember(gettoMember);
        return jwtService.issueJwtToken(member.getIdx(), "KAKAO", authInfoDTO.getId());
    }

    private GettoMember createGettoMember(KakaoMemberInfo kakaoMemberInfo) {
        return GettoMember.builder()
                .oauthOrganization("KAKAO")
                .oauthMember(kakaoMemberInfo)
                .isActivate(true)
                .build();
    }

    public LoginDTO generateLoginDTO(String code) {
        KakaoAuthResponseDTO kakaoAuth = userAuthService.getAuthResponse(code);
        KakaoAuthInfoDTO authUserInfo = this.userAuthService.getAuthUserInfo(kakaoAuth.getAccessToken());

        TokenDTO gettoLoginToken = this.getGettoLoginToken(kakaoAuth);
        KakaoMemberInfo userInfo = this.kakaoMemberInfoService.findKaKaoMemberInfo(authUserInfo.getId());

        return LoginDTO.builder()
                .loginToken(gettoLoginToken)
                .nickname(userInfo.getNickName())
                .thumbnailImageUrl(userInfo.getThumbnailImageUrl())
                .build();
    }
}

