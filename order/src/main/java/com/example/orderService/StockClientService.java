package com.example.orderService;


import com.m23itsolution.stock.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {
  @GrpcClient("stockService")
  private StockServiceGrpc.StockServiceBlockingStub stockStub;

  public boolean checkStock(String productId, int quantity) {
    CheckStockRequest request = CheckStockRequest.newBuilder()
        .setProductId(productId)
        .setQuantity(quantity)
        .build();
    CheckStockResponse response = stockStub.checkStock(request);
    return response.getIsAvailable();
  }

  public boolean updateStock(String productId, int quantity) {
    UpdateStockRequest request = UpdateStockRequest.newBuilder()
        .setProductId(productId)
        .setQuantity(quantity)
        .build();
    UpdateStockResponse response = stockStub.updateStock(request);
    return response.getSuccess();
  }

  public int getStock(String productId) {
    GetStockRequest request = GetStockRequest.newBuilder()
            .setProductId(productId)
            .build();

    GetStockResponse response = stockStub.getStock(request);
    return response.getQuantity();
  }

}