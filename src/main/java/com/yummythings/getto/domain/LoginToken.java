package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_token")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginToken {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "refresh_token", length = 1000)
    private String refreshToken;

    @Column(name = "member_idx")
    private Long memberIdx;

    @Column(name = "isExpired")
    private Boolean isExpired;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}