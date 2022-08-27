package com.yummythings.getto.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "lucky_number")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LuckyNumber implements Serializable {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "round", unique = true)
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

    @Column(name = "bonus")
    private Integer bonus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
