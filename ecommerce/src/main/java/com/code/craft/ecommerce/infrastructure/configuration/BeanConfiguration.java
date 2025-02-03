package com.code.craft.ecommerce.infrastructure.configuration;

import com.code.craft.ecommerce.application.repository.ProductRepository;
import com.code.craft.ecommerce.application.service.ProductService;
import com.code.craft.ecommerce.application.service.UploadFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ProductService productService(ProductRepository productRepository,
                                         UploadFile uploadFile) {
        return new ProductService(productRepository, uploadFile);
    }

    @Bean
    public UploadFile uploadFile() {
        return new UploadFile();
    }
}
