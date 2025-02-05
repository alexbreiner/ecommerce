package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.Stock;

import java.util.List;

public interface StockRepository {
    Stock saveStock(Stock stock);
    List<Stock> getStockByProduct(Product product);
}
