package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.*;
import com.code.craft.ecommerce.domain.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/order")
@Slf4j
public class OrderController {
    private static final Integer UNIT_IN = 0;
    public static final String VANTA = "vanta";
    public static final String ID_USER_SESION = "idUser";

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
    public String showSumaryOrder(Model model, HttpSession httpSession) {
        log.info("Create summary order..");
        log.info("Id user desde la variable de session: {}", httpSession.getAttribute(ID_USER_SESION).toString());
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute(ID_USER_SESION).toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalPriceCart());
        model.addAttribute("user", user);
        model.addAttribute("id", httpSession.getAttribute("idUser").toString());
        return "user/sumaryorder";
    }

    @GetMapping("/create-order")
    public String createOrder(HttpSession httpSession, RedirectAttributes attributes) {
        log.info("Create order..");
        log.info("Id user desde la variable de session: {}", httpSession.getAttribute(ID_USER_SESION).toString());
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute(ID_USER_SESION).toString()));

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
                    stock.setDescription(VANTA);
                    stock.setUnitIn(UNIT_IN);
                    stock.setUnitOut(orderProduct.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));
                }
        );
        //vacia el carrito de compra
        cartService.removeAllItemsCart();
        attributes.addFlashAttribute("id", httpSession.getAttribute("idUser").toString());
        return "redirect:/home";
    }

}
