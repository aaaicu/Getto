package com.yummythings.getto.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LuckyNumberData(Long totSellamnt, Long firstWinamnt, Long firstAccumamnt, String returnValue,
                              String drwNoDate, Integer firstPrzwnerCo, Integer drwtNo1, Integer drwtNo2,
                              Integer drwtNo3, Integer drwtNo4, Integer drwtNo5, Integer drwtNo6, Integer bnusNo) implements Serializable {

    public LuckyNumberData() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
