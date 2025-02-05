package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.application.repository.StockRepository;
import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.Stock;

import java.util.List;

public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.saveStock(stock);
    }

    public List<Stock> getStockByProduct (Product product) {
        return stockRepository.getStockByProduct(product);
    }

}
