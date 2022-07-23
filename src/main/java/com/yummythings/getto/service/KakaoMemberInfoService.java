package com.yummythings.getto.service;

import com.yummythings.getto.domain.KakaoMemberInfo;
import com.yummythings.getto.repository.KakaoMemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KakaoMemberInfoService {
    private final KakaoMemberInfoRepository kakaoMemberInfoRepository;
    public KakaoMemberInfo findKaKaoMemberInfo(String kakaoId) {
        return kakaoMemberInfoRepository.findOneKakaoMemberInfoByKakaoId(kakaoId);
    }
    public KakaoMemberInfo saveKakaoMember(KakaoMemberInfo info) {
        return kakaoMemberInfoRepository.save(info);
    }
}
