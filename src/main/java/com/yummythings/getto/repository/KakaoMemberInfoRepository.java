package com.yummythings.getto.repository;

import com.yummythings.getto.domain.KakaoMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KakaoMemberInfoRepository extends JpaRepository<KakaoMemberInfo, Long> {
    List<KakaoMemberInfo> findFirstKakaoMemberInfoByKakaoId(String kakaoId);
    KakaoMemberInfo findOneKakaoMemberInfoByKakaoId(String kakaoId);

    KakaoMemberInfo findOneKakaoMemberInfoByGettoMemberIdx(Long gettoIdx);
}
