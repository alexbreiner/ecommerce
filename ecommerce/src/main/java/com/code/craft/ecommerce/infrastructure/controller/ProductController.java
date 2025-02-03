package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.ProductService;
import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }

    @PostMapping("/save-product")
    public String seveProduct(Product product) {
        log.info("Nombre de producto: {}", product);
        productService.saveProduct(product);
        //return "admin/products/create";
        return "redirect:/admin";
    }

    @GetMapping("/show")
    public String showProduct(Model model) {
        User user = new User();
        user.setId(1);
        Iterable<Product> products = productService.getProductsByUser(user);

        model.addAttribute("products", products);
        return "admin/products/show";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        log.info("Editando producto con id: {}", id);
        Product product = productService.getProductById(id);

        log.info("Producto obtenido: {}", product);
        model.addAttribute("product", product);

        return "admin/products/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct( @PathVariable Integer id) {
        log.info("Eliminando producto con id: {}", id);

        productService.deleteProductById(id);

        return "redirect:/admin/products/show";
    }

}
