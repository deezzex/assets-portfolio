package com.deezzex.aggregator.client;

import com.deezzex.shared.dto.GetAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FiatServiceClient {

    private final RestTemplate restTemplate;

    @Value("${service.fiat.url}")
    private String baseUrl;

    public List<GetAccountDto> getAccountsByUserId(Integer userId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/accounts")
                .queryParam("userId", userId)
                .toUriString();

        ResponseEntity<GetAccountDto[]> response = restTemplate.getForEntity(url, GetAccountDto[].class);

        return List.of(response.getBody());
    }
}
