package com.yummythings.getto.service;

import com.yummythings.getto.service.dto.KakaoAuthResponseDTO;
import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("local")
class JwtServiceTest {

    @Test
    public void 회원등록() {
        KakaoAuthResponseDTO authResponseDTO = new KakaoAuthResponseDTO("", "1234567","1", "kakao",10000, List.of(""), 10000);

//        Assertions.assertThat(result).isTrue();
    }

}