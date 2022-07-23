package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oauth_member_idx")
    private KakaoMemberInfo oauthMember;

    @Column(name = "oauth_organization", length = 100)
    private String oauthOrganization;

    @Column(name = "is_activate")
    private Boolean isActivate;

}
