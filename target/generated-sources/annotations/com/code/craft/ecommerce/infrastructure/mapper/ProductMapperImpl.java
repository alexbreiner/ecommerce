package com.code.craft.ecommerce.infrastructure.mapper;

import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.infrastructure.entity.ProductEntity;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-27T16:18:32-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Product toProduct(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productEntity.getId() );
        product.setCode( productEntity.getCode() );
        product.setName( productEntity.getName() );
        product.setDescription( productEntity.getDescription() );
        product.setImage( productEntity.getImage() );
        product.setPrice( productEntity.getPrice() );
        product.setDateCreated( productEntity.getDateCreated() );
        product.setDateUpdated( productEntity.getDateUpdated() );
        product.setUser( userMapper.toUser( productEntity.getUserEntity() ) );

        return product;
    }

    @Override
    public Iterable<Product> toProducts(Iterable<ProductEntity> productsEntities) {
        if ( productsEntities == null ) {
            return null;
        }

        ArrayList<Product> iterable = new ArrayList<Product>();
        for ( ProductEntity productEntity : productsEntities ) {
            iterable.add( toProduct( productEntity ) );
        }

        return iterable;
    }

    @Override
    public ProductEntity toProductEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.getId() );
        productEntity.setCode( product.getCode() );
        productEntity.setName( product.getName() );
        productEntity.setDescription( product.getDescription() );
        productEntity.setImage( product.getImage() );
        productEntity.setPrice( product.getPrice() );
        productEntity.setDateCreated( product.getDateCreated() );
        productEntity.setDateUpdated( product.getDateUpdated() );
        productEntity.setUserEntity( userMapper.toUserEntity( product.getUser() ) );

        return productEntity;
    }
}
