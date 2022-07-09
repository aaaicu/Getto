package com.yummythings.getto.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yummythings.getto.vo.AgeRange;
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
    private Long id;
    private LocalDateTime connectedAt;
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    static class KakaoAccount{
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
    static class Profile{
        private String nickname;
        private String thumbnailImageUrl;
        private String profileImageUrl;
        private Boolean isDefaultImage;
    }






//    {"id":2310814960,"connected_at":"2022-07-09T13:50:05Z",
//
//            "properties":{"nickname":"재현","profile_image":"http://k.kakaocdn.net/dn/NS359/btrFciz0IjH/XlOTWYDb5kIkhqpMl5vTTK/img_640x640.jpg","thumbnail_image":"http://k.kakaocdn.net/dn/NS359/btrFciz0IjH/XlOTWYDb5kIkhqpMl5vTTK/img_110x110.jpg"},
//            "kakao_account":{
//                "profile_nickname_needs_agreement":false,"profile_image_needs_agreement":false,
//                "profile":{"nickname":"재현","thumbnail_image_url":"http://k.kakaocdn.net/dn/NS359/btrFciz0IjH/XlOTWYDb5kIkhqpMl5vTTK/img_110x110.jpg","profile_image_url":"http://k.kakaocdn.net/dn/NS359/btrFciz0IjH/XlOTWYDb5kIkhqpMl5vTTK/img_640x640.jpg","is_default_image":false},
//                "has_age_range":true,
//                "age_range_needs_agreement":false,
//                "age_range":"30~39"
//            }
//    }

//    @JsonCreator
//    public KakaoAuthInfoDTO(@JsonProperty("scope") String scope) {
//        this.scope = parseScope(scope);
//    }

//    public List<String> parsePropertyKeys(String propertyKeys) {
//        String[] scopes = propertyKeys.split(" ");
//        return Arrays.stream(scopes).map(String::trim).collect(Collectors.toList());
//    }
}
