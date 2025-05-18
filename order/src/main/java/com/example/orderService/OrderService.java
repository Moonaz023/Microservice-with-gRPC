package com.example.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired
  private StockClientService stockClientService;

  public String placeOrder(String productId, int quantity) {
    if (!stockClientService.checkStock(productId, quantity)) {
      return "Order failed: Not enough stock for " + productId;
    }
    if (stockClientService.updateStock(productId, quantity)) {
      return "Order placed successfully for " + productId;
    } else {
      return "Order failed: Could not update stock for " + productId;
    }
  }
  public int getStock(String productId) {
    return stockClientService.getStock(productId);
  }
}