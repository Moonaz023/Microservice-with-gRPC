package com.example.stockService.service;

import com.m23itsolution.stock.GetStockResponse;

public interface StockService {
  boolean checkStock(String productId, int quantity);

  boolean updateStock(String productId, int quantity);

  GetStockResponse getStockResponse(String productId);

  GetStockResponse addToStock(String productId, int quantity);
}
