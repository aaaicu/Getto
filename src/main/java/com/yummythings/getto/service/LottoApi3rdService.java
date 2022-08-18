package com.yummythings.getto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yummythings.getto.service.dto.LuckyNumberData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
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

    public LuckyNumberData getLuckyNumber(Integer roundNumber){

        String url = BASE_URL + "&drwNo=" + roundNumber;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        try {
            return objectMapper.readValue( exchange.getBody(), LuckyNumberData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new LuckyNumberData();
    }
}
