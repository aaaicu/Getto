package com.yummythings.getto.endpoint.lotto.data.resposne;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LuckyNumberResponse {
        private Integer round;
        private Integer number1;
        private Integer number2;
        private Integer number3;
        private Integer number4;
        private Integer number5;
        private Integer number6;
        private Integer bonus;
        private String createdAt;
}
