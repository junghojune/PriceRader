package com.cha.priceradar.product.scheduler;

import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.naver.service.NaverService;
import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.product.repository.ProductRepository;
import com.cha.priceradar.product.service.ProductService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final ProductService productService;
    private final NaverService naverService;
    private final ProductRepository productRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시
    public void updatePrice() throws InterruptedException {
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            // 1초에 한 상품 씩 조회합니다 (NAVER 제한)
            TimeUnit.SECONDS.sleep(1);

            // i 번째 관심 상품의 제목으로 검색을 실행합니다.
            String keyword = product.getName();
            ItemDto item = naverService.getItems(keyword);

            productService.updateProductInfo(product, item);
        }
    }
}
