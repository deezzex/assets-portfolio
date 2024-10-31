package com.deezzex.aggregator.client;

import com.deezzex.shared.dto.GetWalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CryptoServiceClient {

    private final RestTemplate restTemplate;

    @Value("${service.crypto.url}")
    private String baseUrl;

    public List<GetWalletDto> getWalletsByUserId(Integer userId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/wallets")
                .queryParam("userId", userId)
                .toUriString();

        ResponseEntity<GetWalletDto[]> response = restTemplate.getForEntity(url, GetWalletDto[].class);

        return List.of(response.getBody());
    }
}
