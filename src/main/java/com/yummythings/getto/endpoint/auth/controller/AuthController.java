package com.yummythings.getto.endpoint.auth.controller;

import com.yummythings.getto.endpoint.auth.facade.AuthFacade;
import com.yummythings.getto.endpoint.oauth.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @GetMapping("/reissue")
    public ResponseEntity<LoginResponseDTO> reissueAccessToken(
            @RequestParam(required = false) String refreshToken,
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            HttpServletResponse response) {
        LoginResponseDTO loginResponseDTO = authFacade.reissueToken(refreshToken, accessToken, response);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping
    public ResponseEntity<String> testAuth( @CookieValue("refreshToken") String refreshToken) {
        System.out.println("refreshToken = " + refreshToken);

        return ResponseEntity.ok("ok");
    }

}
