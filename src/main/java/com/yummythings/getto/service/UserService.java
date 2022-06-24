package com.yummythings.getto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final KakaoOAuth2 kakaoOAuth2;
}
