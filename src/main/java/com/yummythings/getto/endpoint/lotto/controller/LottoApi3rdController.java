package com.yummythings.getto.endpoint.lotto.controller;

import com.yummythings.getto.endpoint.lotto.facade.LottoApi3rdFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class LottoApi3rdController {

    private final LottoApi3rdFacade lottoApi3rdFacade;

    @GetMapping("/last/lucky-number")
    public String lastLuckyNumber() {

        return lottoApi3rdFacade.getLastLuckyNumber().toString();
    }


    @GetMapping("/last/lucky-number/refresh")
    public void refreshCachedLuckyNumber() {
        lottoApi3rdFacade.refreshCachedLuckyNumber();
    }


}
