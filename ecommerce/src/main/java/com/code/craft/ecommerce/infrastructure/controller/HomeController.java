package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.ProductService;
import com.code.craft.ecommerce.application.service.StockService;
import com.code.craft.ecommerce.domain.Stock;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {
    private final ProductService productService;
    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    @GetMapping
    public String home(Model model, HttpSession httpSession) {
        model.addAttribute("products", productService.getProducts());
        try {
            model.addAttribute("id", httpSession.getAttribute("idUser").toString());
        } catch (Exception e) {
            getError(e);
        }
        return "home";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model, HttpSession httpSession) {
        List<Stock> stocks = stockService.getStockByProduct(productService.getProductById(id));

        log.info("Id product: {}", id);
        log.info("Stock size: {}", stocks.size());

        Integer lastBalance = stocks.get(stocks.size() - 1).getBalance();
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("stock", lastBalance);

        try {
            model.addAttribute("id", httpSession.getAttribute("idUser").toString());
        } catch (Exception e) {
            this.getError(e);
        }

        return "user/productDetail";
    }

    private String getError(Exception e) {
        return e.getMessage();
    }

}
