package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.ProductService;
import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String seveProduct(Product product, @RequestParam("img") MultipartFile multipartFile,
                              HttpSession httpSession) throws Exception {
        log.info("Nombre de producto: {}", product);
        productService.saveProduct(product, multipartFile, httpSession);
        return "redirect:/admin";
    }

    @GetMapping("/show")
    public String showProduct(Model model, HttpSession httpSession) {
        log.info("Id user desde la variable de session: {}", httpSession.getAttribute("idUser").toString());

        User user = new User();
        user.setId(Integer.parseInt(httpSession.getAttribute("idUser").toString()));
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
