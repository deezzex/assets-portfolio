package com.deezzex.aggregator.ingress.controller;

import com.deezzex.aggregator.dto.GetUserAssetsDto;
import com.deezzex.aggregator.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assets")
@RequiredArgsConstructor
public class UserAssetsController {

    private final AssetsService assetsService;

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserAssetsDto> getUserAssets(@PathVariable Integer userId) {
        GetUserAssetsDto userAssets = assetsService.getUserAssets(userId);

        return ResponseEntity.ok(userAssets);
    }
}
