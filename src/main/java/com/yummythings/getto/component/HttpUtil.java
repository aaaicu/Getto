package com.yummythings.getto.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpUtil {
    public HttpHeaders getAuthorizationHeader(String authorization) {
        HttpHeaders headers = getDefaultHeader();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + authorization);
        return headers;
    }

    public HttpHeaders getDefaultHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }

    public <T> ResponseEntity<T> post(String url, MultiValueMap<String, String> headers, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(headers), clazz);
    }

    public <T> ResponseEntity<T> post(String url, MultiValueMap<String, String> headers, Object body, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body,headers), clazz);
    }
}
