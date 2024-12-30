package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.User;

//Gateway
public interface ProductRepository {
    Iterable<Product> getProducts();
    Iterable<Product> getProductsByUser(User user);
    Product getProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProductById(Integer id);
}
