package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.Stock;

import java.util.List;

public class ValidateStock {

    public static final String OPERATION_ADD = "sumar";
    public static final String OPERATION_SUBTRACTION = "restar";

    private final StockService stockService;

    public ValidateStock(StockService stockService) {
        this.stockService = stockService;
    }

    // Validar si existe un balance de un producto en stock
    private boolean existBalance(Product product) {
        List<Stock> stockList = stockService.getStockByProduct(product);
        System.out.println("stockList existBalance = " + stockList);
        return stockList.isEmpty() ? false : true;
    }

    // Calcular el balance de un producto
    public Stock calculateBalance(Stock stock) {
        Integer balance = 0;
        System.out.println("balance = " + balance);

        if (stock.getUnitIn() != 0) {

            System.out.println("stock.getUnitIn() = " + stock.getUnitIn());

            if (existBalance(stock.getProduct())) {

                balance = getStockProduct(stock);

                System.out.println("new balance = " + balance);

                validOperation(OPERATION_ADD, stock, balance);
            } else {
                // si no existe un balance de un producto en stock
                stock.setBalance(stock.getUnitIn());
            }
        } else {
            balance = getStockProduct(stock);
            System.out.println("old balance = " + balance);
            validOperation(OPERATION_SUBTRACTION, stock, balance);
        }

        return stock;
    }

    // Obtener el ultimo stock de un producto
    private Integer getStockProduct(Stock stock) {
        List<Stock> stockList = stockService.getStockByProduct(stock.getProduct());
        System.out.println("stockList = " + stockList);
        return stockList.get(stockList.size() -1).getBalance();
    }

    //sumar la entradas de stock
    private void sumarBalance(Stock stock, Integer balance) {
        stock.setBalance(balance + stock.getUnitIn());
        System.out.println("balance + stock.getUnitIn() = " + (balance + stock.getUnitIn()));
    }

    // Restar las salidas de stock
    private void restarBalance(Stock stock, Integer balance) {
        stock.setBalance(balance - stock.getUnitOut());
        System.out.println("balance - stock.getUnitOut() = " + (balance - stock.getUnitOut()));
    }

    // Validar si la operacion es valida
    public void validOperation (String operation, Stock stock, Integer balance) {
        // como valido para retornar la suma o la resta
        if (OPERATION_ADD.equalsIgnoreCase(operation)) {
            sumarBalance(stock, balance);
        } else if (OPERATION_SUBTRACTION.equalsIgnoreCase(operation)) {
            restarBalance(stock, balance);
        }
    }

}
