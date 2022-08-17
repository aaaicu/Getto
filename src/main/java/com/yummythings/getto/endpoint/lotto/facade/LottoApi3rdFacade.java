package com.yummythings.getto.endpoint.lotto.facade;

import com.yummythings.getto.service.dto.LottoNumberData;
import com.yummythings.getto.service.LottoApi3rdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
public class LottoApi3rdFacade {

    private final LottoApi3rdService lottoApi3rdService;

    public LottoNumberData lastLuckyNumber(Integer roundNumber) {

        return lottoApi3rdService.lastLuckyNumber(roundNumber);
    }

}
