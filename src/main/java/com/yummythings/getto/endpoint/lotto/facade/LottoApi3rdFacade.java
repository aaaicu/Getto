package com.yummythings.getto.endpoint.lotto.facade;

import com.yummythings.getto.common.component.LocalDateUtil;
import com.yummythings.getto.domain.LuckyNumber;
import com.yummythings.getto.endpoint.lotto.data.resposne.LuckyNumberResponse;
import com.yummythings.getto.service.LottoApi3rdService;
import com.yummythings.getto.service.LuckyNumberService;
import com.yummythings.getto.service.dto.LuckyNumberData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class LottoApi3rdFacade {

    private final LottoApi3rdService lottoApi3rdService;
    private final LuckyNumberService luckyNumberService;

    public LuckyNumberResponse getLastLuckyNumber() {
        LuckyNumber luckyNumber = luckyNumberService.findLastLuckyNumber().orElse(new LuckyNumber());
        return LuckyNumberResponse.builder()
                .round(luckyNumber.getRound())
                .number1(luckyNumber.getNumber1())
                .number2(luckyNumber.getNumber2())
                .number3(luckyNumber.getNumber3())
                .number4(luckyNumber.getNumber4())
                .number5(luckyNumber.getNumber5())
                .number6(luckyNumber.getNumber6())
                .bonus(luckyNumber.getBonus())
                .createdAt(LocalDateUtil.LocalDateTimeToKorString(luckyNumber.getCreatedAt()))
                .build();
    }

    public void refreshCachedLuckyNumber() {
        luckyNumberService.refreshCachedLuckyNumber();
    }

    /**
     * 토요일 오후 9시부터 10시까지 10분간격으로 조회해서 업데이트
     */
    @Scheduled(cron = "* 0/10 21-22 * * 6")
//    @Scheduled(cron = "0/10 * * * * *")
    public void resetLuckyNumber() {

        LocalDateTime startLocalDateTime = LocalDateTime.of(2002, 12, 7, 21, 0);
        int roundNumber = ((int) startLocalDateTime.until(LocalDateTime.now(), ChronoUnit.WEEKS)) + 1;

        if (luckyNumberService.findLuckyNumber(roundNumber).isEmpty()) {
            LuckyNumberData thisRoundLuckyNumber = lottoApi3rdService.getLuckyNumber(roundNumber);

            if ("success".equals(thisRoundLuckyNumber.returnValue())) {
                luckyNumberService.save(extractLuckyNumber(roundNumber, thisRoundLuckyNumber));

                luckyNumberService.refreshCachedLuckyNumber();
                luckyNumberService.findLastLuckyNumber();
            }

        }
    }

    private LuckyNumber extractLuckyNumber(int roundNumber, LuckyNumberData luckyNumber) {
        return new LuckyNumber(null, roundNumber, luckyNumber.drwtNo1(), luckyNumber.drwtNo2(), luckyNumber.drwtNo3(), luckyNumber.drwtNo4(), luckyNumber.drwtNo5(), luckyNumber.drwtNo6(), luckyNumber.bnusNo(), LocalDateTime.now());
    }
}
