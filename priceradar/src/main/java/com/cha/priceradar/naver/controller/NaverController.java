package com.cha.priceradar.naver.controller;

import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.naver.service.NaverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/naver")
public class NaverController {

    private final NaverService naverService;

    @GetMapping
    public List<ItemDto> searchNaver(@RequestParam String keyword) {
        return naverService.searchProduct(keyword);
    }
}
