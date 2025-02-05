package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/user/cart")
@Slf4j
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public String addProductCart(@RequestParam Integer quantity,
                                 @RequestParam Integer idProduct,
                                 @RequestParam String nameProduct,
                                 @RequestParam BigDecimal price){

        cartService.addItemCard(quantity, idProduct, nameProduct, price);

        cartService.getItemCarts().forEach(
                itemCart -> log.info("Items cart: {}", itemCart)
        );

        return "redirect:/home";
    }

}
