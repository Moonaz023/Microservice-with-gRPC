package com.example.stockService.grpc;

import com.example.stockService.service.StockService;
import com.m23itsolution.stock.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class StockGrpcServiceImpl extends StockServiceGrpc.StockServiceImplBase {
  private final StockService stockService;

  @Override
  public void checkStock(
      CheckStockRequest request, StreamObserver<CheckStockResponse> responseObserver) {
    String productId = request.getProductId();
    int quantity = request.getQuantity();
    boolean isAvailable = stockService.checkStock(productId, quantity);
    String message = isAvailable ? "Stock available" : "Not enough stock";

    CheckStockResponse response =
        CheckStockResponse.newBuilder().setIsAvailable(isAvailable).setMessage(message).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void updateStock(
      UpdateStockRequest request, StreamObserver<UpdateStockResponse> responseObserver) {
    String productId = request.getProductId();
    int quantity = request.getQuantity();
    boolean success = stockService.updateStock(productId, quantity);
    String message = success ? "Stock updated" : "Failed to update stock";

    UpdateStockResponse response =
        UpdateStockResponse.newBuilder().setSuccess(success).setMessage(message).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getStock(GetStockRequest request, StreamObserver<GetStockResponse> responseObserver) {
    String productId = request.getProductId();
    GetStockResponse response = stockService.getStockResponse(productId);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void addToStock(
      AddToStockRequest request, StreamObserver<GetStockResponse> responseObserver) {
    GetStockResponse response =
        stockService.addToStock(request.getProductId(), request.getQuantity());
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
