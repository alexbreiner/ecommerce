package com.code.craft.ecommerce.infrastructure.configuration;

import com.code.craft.ecommerce.application.repository.ProductRepository;
import com.code.craft.ecommerce.application.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
