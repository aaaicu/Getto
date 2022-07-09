package com.yummythings.getto.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AgeRangeTest {

    @Test
    void 나이범위() {
        String range = "10~30";
        AgeRange ageRange1 = new AgeRange(range);
        AgeRange ageRange2 = new AgeRange(range);
        Assertions.assertThat(ageRange1.equals(ageRange2)).isEqualTo(true);
        Assertions.assertThat(ageRange1.getMaximum()).isEqualTo(30);
        Assertions.assertThat(ageRange1.getMinimum()).isEqualTo(10);
    }

}