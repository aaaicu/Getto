package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_lotto_number")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLottoNumber {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "round")
    private Integer round;

    @Column(name = "number1")
    private Integer number1;

    @Column(name = "number2")
    private Integer number2;

    @Column(name = "number3")
    private Integer number3;

    @Column(name = "number4")
    private Integer number4;

    @Column(name = "number5")
    private Integer number5;

    @Column(name = "number6")
    private Integer number6;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private GettoMember gettoMember;

    @Enumerated(value = EnumType.STRING)
    private Winning winning;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}
