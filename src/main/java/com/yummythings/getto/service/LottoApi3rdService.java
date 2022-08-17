package com.yummythings.getto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yummythings.getto.service.dto.LottoNumberData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LottoApi3rdService {
    public static String BASE_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    @Cacheable(value = "findLottoCache")
    public LottoNumberData lastLuckyNumber(Integer roundNumber){

        String url = BASE_URL + "&drwNo=" + roundNumber;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        try {
            return objectMapper.readValue( exchange.getBody(), LottoNumberData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new LottoNumberData();
    }

    @CacheEvict(value = "findLottoCache", allEntries = true)
    public void refreshCachedLottoNumber() {
    }
}
