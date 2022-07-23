package com.yummythings.getto.endpoint.oauth;

import com.yummythings.getto.dto.LoginDTO;
import com.yummythings.getto.endpoint.oauth.facade.OAuthFacade;
import com.yummythings.getto.endpoint.oauth.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthFacade oAuthFacade;

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<LoginResponseDTO> getLoginTokenByKakao(@RequestParam String code, HttpServletResponse response) {

        LoginDTO loginDTO = oAuthFacade.generateLoginDTO(code);
        response.setHeader("Set-Cookie", createRefreshTokenCookie(loginDTO.getLoginToken().getRefreshToken()));

        return ResponseEntity.ok(LoginDTO.extract(loginDTO));
    }

    private String createRefreshTokenCookie(String refreshToken) {
        final int twoWeeks = 14 * 24 * 60 * 60;

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(twoWeeks)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        return cookie.toString();
    }

}
