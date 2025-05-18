package com.example.production.grpc;

import com.m23itsolution.stock.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockGrpcClientService {
    @GrpcClient("stockService")
    private StockServiceGrpc.StockServiceBlockingStub stockStub;

    public int addToStock(String productId, int quantity) {
        AddToStockRequest request = AddToStockRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();
        GetStockResponse response = stockStub.addToStock(request);
        return response.getQuantity();
    }

}