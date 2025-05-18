package com.example.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping("/order")
  public String placeOrder(@RequestParam String productId, @RequestParam int quantity) {
    return orderService.placeOrder(productId, quantity);
  }
  @GetMapping("/stock")
  public int getStockResponse(@RequestParam String productId)
  {
    return orderService.getStock(productId);
  }
}