package com.yummythings.getto.common.vo;

import lombok.*;

@EqualsAndHashCode
@Getter
public class AgeRange {
    private AgeRange() {
    }

    public AgeRange(String ageRange) {
        String[] splitValue = ageRange.split("~");
        if (splitValue.length != 2 ) {
            throw new IllegalArgumentException("범위 형식이 잘못되었습니다.");
        }
        this.minimum = Integer.parseInt(splitValue[0]);
        this.maximum = Integer.parseInt(splitValue[1]);
    }

    private int minimum;
    private int maximum;

    @Override
    public String toString() {
        return minimum + " ~ " + maximum;
    }

}
