package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "getto_member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GettoMember {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    // TODO : 연관관계 방향 변경 필요
//    @OneToMany(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "oauth_member_idx")
    @OneToMany(mappedBy = "gettoMember")
    private List<KakaoMemberInfo> oauthMember = new ArrayList<>();

//    @Column(name = "oauth_organization", length = 100)
//    private String oauthOrganization;

    @Column(name = "is_activate")
    private Boolean isActivate;

}
