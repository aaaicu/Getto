package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kakao_member_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMemberInfo {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "kakao_id")
    private String kakaoId;

    @Column(name = "nick_name", length = 50)
    private String nickName;

    @Column(name = "age_range_min")
    private Integer ageRangeMin;

    @Column(name = "age_range_max")
    private Integer ageRangeMax;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @ManyToOne
    @JoinColumn(name = "oauth_member_idx")
    private GettoMember gettoMember;
}