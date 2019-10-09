package com.mbabski.swapi.report.save;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
class ApiConnector {

    private final RestTemplate restTemplate = new RestTemplate();

    <T> T getSwapiResponse(String apiUrl, Class<T> tClass) {
        HttpEntity<T> entity = new HttpEntity<T>(getHttpHeaders());
        ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, tClass);
        HttpStatus statusCode = response.getStatusCode();
        return (statusCode == HttpStatus.OK) ? response.getBody() : null;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return headers;
    }
}
