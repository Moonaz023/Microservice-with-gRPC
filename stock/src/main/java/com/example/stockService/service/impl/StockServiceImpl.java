package com.example.stockService.service.impl;

import com.example.stockService.entity.StockEntity;
import com.example.stockService.repository.StockRepository;
import com.example.stockService.service.StockService;
import com.m23itsolution.stock.GetStockResponse;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
  private final StockRepository stockRepository;

  // Check if enough stock is available
  @Override
  public boolean checkStock(String productId, int quantity) {
    return stockRepository
        .findByProductId(productId)
        .map(stock -> stock.getQuantity() >= quantity)
        .orElse(false);
  }

  // Reduce stock if available
  @Override
  public boolean updateStock(String productId, int quantity) {
    StockEntity currentStock =
        stockRepository.findByProductId(productId).orElseThrow(NoSuchElementException::new);
    int currentQuantity = currentStock.getQuantity();

    if (currentQuantity >= quantity) {
      currentStock.setQuantity(currentQuantity - quantity);
      stockRepository.save(currentStock);
      return true;
    }
    return false;
  }

  @Override
  public GetStockResponse getStockResponse(String productId) {
    StockEntity stockEntity =
        stockRepository.findByProductId(productId).orElseThrow(NoSuchElementException::new);
    return GetStockResponse.newBuilder()
        .setProductId(stockEntity.getProductId())
        .setQuantity(stockEntity.getQuantity())
        .build();
  }

  @Override
  public GetStockResponse addToStock(String productId, int quantity) {
    StockEntity stockEntity =
        stockRepository.findByProductId(productId).orElseThrow(NoSuchElementException::new);
    stockEntity.setQuantity(stockEntity.getQuantity() + quantity);
    stockRepository.save(stockEntity);
    return GetStockResponse.newBuilder()
        .setProductId(stockEntity.getProductId())
        .setQuantity(stockEntity.getQuantity())
        .build();
  }
}
