package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.*;
import com.code.craft.ecommerce.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/order")
@Slf4j
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final StockService stockService;
    private final ValidateStock validateStock;

    public OrderController(CartService cartService,
                           UserService userService,
                           ProductService productService,
                           OrderService orderService,
                           OrderProductService orderProductService,
                           StockService stockService,
                           ValidateStock validateStock) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
    }


    @GetMapping("/sumary-order")
    public String showSumaryOrder(Model model) {
        User user = userService.findById(1);

        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalPriceCart());
        model.addAttribute("user", user);

        return "user/sumaryorder";
    }

    @GetMapping("/create-order")
    public String createOrder() {
        log.info("Create order..");
        //obtener el user temporal
        User user = userService.findById(1);

        //crear order
        Order order = new Order();
        order.setDateCreated(LocalDateTime.now());
        order.setUser(user);

        order = orderService.createOrder(order);

        //Order product
        List<OrderProduct> orderProducts = new ArrayList<>();

        //item cart - orderproduct

        for (ItemCart itemCart: cartService.getItemCarts()) {
            orderProducts.add(
                    new OrderProduct(
                            productService.getProductById(itemCart.getIdProduct()),
                            itemCart.getQuantity(),
                            order
                    ));
        }

        //save
        orderProducts.forEach(
                orderProduct -> {
                    orderProductService.create(orderProduct);
                    Stock stock = new Stock();
                    stock.setDateCreated(LocalDateTime.now());
                    stock.setProduct(orderProduct.getProduct());
                    stock.setDescription("vanta");
                    stock.setUnitIn(0);
                    stock.setUnitOut(orderProduct.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                }
        );

        cartService.removeAllItemsCart();

        return "redirect:/home";
    }

}
