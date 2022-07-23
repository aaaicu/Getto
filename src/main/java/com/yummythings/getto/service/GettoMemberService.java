package com.yummythings.getto.service;

import com.yummythings.getto.common.component.TokenProvider;
import com.yummythings.getto.domain.GettoMember;
import com.yummythings.getto.domain.KakaoMemberInfo;
import com.yummythings.getto.repository.GettoMemberRepository;
import com.yummythings.getto.repository.KakaoMemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GettoMemberService {
    private final TokenProvider tokenProvider;
    private final GettoMemberRepository gettoMemberRepository;
    private final KakaoMemberInfoRepository kakaoMemberInfoRepository;

    public Boolean isRegisteredMember(String kakaoId) {
        List<KakaoMemberInfo> firstKakaoMemberInfoByKakaoId = kakaoMemberInfoRepository.findFirstKakaoMemberInfoByKakaoId(kakaoId);
        return !firstKakaoMemberInfoByKakaoId.isEmpty();
    }

    public GettoMember saveMember(GettoMember gettoMember) {
        return gettoMemberRepository.save(gettoMember);
    }

    public GettoMember findGettoMember(String oauthOrganization, Long oauthMemberIdx) {
        return gettoMemberRepository.findAllByOauthOrganizationAndOauthMemberIdx(oauthOrganization, oauthMemberIdx);

    }
}
