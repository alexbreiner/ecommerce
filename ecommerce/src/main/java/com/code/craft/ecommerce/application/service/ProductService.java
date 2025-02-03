package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.application.repository.ProductRepository;
import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.User;

import java.time.LocalDateTime;

//caso de uso
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Iterable<Product> getProductsByUser(User user) {
        return productRepository.getProductsByUser(user);
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    public Product saveProduct(Product product) {
        if (product.getId() == null) {
            User user = new User();
            user.setId(1);
            product.setDateCreated(LocalDateTime.now());
            product.setDateUpdated(LocalDateTime.now());
            product.setUser(user);
            return productRepository.saveProduct(product);
        }

        Product productToUpdate = productRepository.getProductById(product.getId());

        product.setCode(productToUpdate.getCode());
        product.setUser(productToUpdate.getUser());
        product.setDateCreated(productToUpdate.getDateCreated());
        product.setDateUpdated(LocalDateTime.now());

        return productRepository.saveProduct(product);

    }

    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }
}
