package com.yummythings.getto.endpoint.oauth.controller;

import com.yummythings.getto.dto.LoginDTO;
import com.yummythings.getto.endpoint.oauth.facade.OAuthFacade;
import com.yummythings.getto.endpoint.oauth.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthFacade oAuthFacade;

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<LoginResponseDTO> getLoginTokenByKakao(@RequestParam String code, HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(LoginDTO.extract(oAuthFacade.generateLoginDTO(code)));
    }
}
