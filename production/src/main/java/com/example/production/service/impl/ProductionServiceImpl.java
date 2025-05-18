package com.example.production.service.impl;

import com.example.production.grpc.StockGrpcClientService;
import com.example.production.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {
    private final StockGrpcClientService stockGrpcClientService;
    @Override
    public int addToStock(String productId,int quantity) {
        return stockGrpcClientService.addToStock(productId,quantity);
    }
}
