package com.example.stockService.repository;

import com.example.stockService.entity.StockEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

  Optional<StockEntity> findByProductId(String productId);
}
