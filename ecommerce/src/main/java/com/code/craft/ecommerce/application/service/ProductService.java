package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.application.repository.ProductRepository;
import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

//caso de uso
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final UploadFile uploadFile;

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
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

    public Product saveProduct(Product product, MultipartFile multipartFile, HttpSession httpSession) throws IOException {
        if (product.getId() == null) {
            User user = new User();
            user.setId(Integer.parseInt(httpSession.getAttribute("idUser").toString()));
            product.setDateCreated(LocalDateTime.now());
            product.setDateUpdated(LocalDateTime.now());
            product.setUser(user);
            log.info("Imagen introducida: {}", multipartFile.getOriginalFilename());
            product.setImage(uploadFile.upload(multipartFile));
        } else {
            Product productToUpdate = productRepository.getProductById(product.getId());

            // Si no se carga la imagen toma la que se guardo al registro
            if (multipartFile.isEmpty()) {
                product.setImage(productToUpdate.getImage());
            } else {
                //guarda la que se le envia actualmente antes se elimina la anterior
                // pero si no es por defecto
                if(!productToUpdate.getImage().equals("default.jpg")) {
                    uploadFile.delete(productToUpdate.getImage());
                }
                product.setImage(uploadFile.upload(multipartFile));
            }
            product.setCode(productToUpdate.getCode());
            product.setUser(productToUpdate.getUser());
            product.setDateCreated(productToUpdate.getDateCreated());
            product.setDateUpdated(LocalDateTime.now());
        }
        return productRepository.saveProduct(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }
}
