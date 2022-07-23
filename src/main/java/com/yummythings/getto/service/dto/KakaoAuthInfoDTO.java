package com.yummythings.getto.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yummythings.getto.common.vo.AgeRange;
import com.yummythings.getto.domain.KakaoMemberInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAuthInfoDTO {
    private String id;
    private LocalDateTime connectedAt;
    private KakaoAccount kakaoAccount;

    /* 중재자 숨기기*/
    public Profile getProfile() {
        return getKakaoAccount().getProfile();
    }

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoAccount{
        private Boolean profileNicknameNeedsAgreement;
        private Boolean profileImageNeedsAgreement;
        private Profile profile;
        private Boolean hasAgeRange;
        private Boolean ageRangeNeedsAgreement;
        private AgeRange ageRange;

        @JsonCreator
        public KakaoAccount(@JsonProperty("age_range") String ageRange) {
            this.ageRange = new AgeRange(ageRange);
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Profile{
        private String nickname;
        private String thumbnailImageUrl;
        private String profileImageUrl;
        private Boolean isDefaultImage;

        public String getProfileImageUrl() {
            String httpString = "http://";
            String httpsString = "https://";
            if (profileImageUrl.contains(httpString)) {
                return profileImageUrl.replaceAll(httpString, httpsString);
            }
            return profileImageUrl;
        }
    }


    public static KakaoMemberInfo extract(KakaoAuthInfoDTO kakaoAuthInfoDTO) {
        return KakaoMemberInfo.builder()
                .kakaoId(kakaoAuthInfoDTO.getId())
                .ageRangeMin(kakaoAuthInfoDTO.getKakaoAccount().getAgeRange().getMinimum())
                .ageRangeMax(kakaoAuthInfoDTO.getKakaoAccount().getAgeRange().getMaximum())
                .nickName(kakaoAuthInfoDTO.getProfile().getNickname())
                .thumbnailImageUrl(kakaoAuthInfoDTO.getProfile().getProfileImageUrl())
                .build();
    }

}
