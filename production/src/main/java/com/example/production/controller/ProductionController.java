package com.example.production.controller;

import com.example.production.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductionController {
    private final ProductionService productionService;

    @PostMapping("/addToStock")
    public int addToStock(@RequestParam String productId, @RequestParam int quantity) {
        return productionService.addToStock(productId, quantity);
    }
}
