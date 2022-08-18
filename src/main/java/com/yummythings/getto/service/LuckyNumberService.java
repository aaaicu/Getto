package com.yummythings.getto.service;

import com.yummythings.getto.domain.LuckyNumber;
import com.yummythings.getto.repository.LuckyNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LuckyNumberService {

    private final LuckyNumberRepository luckyNumberRepository;

    public Optional<LuckyNumber> findLuckyNumber(Integer round) {
        return luckyNumberRepository.findFirstByRound(round);
    }

    public void save(LuckyNumber luckyNumber) {
        luckyNumberRepository.save(luckyNumber);
    }

    @Cacheable(value = "findLottoCache")
    public Optional<LuckyNumber> findLastLuckyNumber() {
        return luckyNumberRepository.findFirstByOrderByRoundDesc();
    }

    @CacheEvict(value = "findLottoCache", allEntries = true)
    public void refreshCachedLuckyNumber() {
    }
}
