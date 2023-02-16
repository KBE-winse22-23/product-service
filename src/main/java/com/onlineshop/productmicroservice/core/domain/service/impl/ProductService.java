package com.onlineshop.productmicroservice.core.domain.service.impl;

import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductService;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Data
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> productFromDB = productRepository.findById(id);

        if(productFromDB.isEmpty()){
            throw new NotFoundException("Product with the given id " + id + " not found!");
        }

        return productFromDB.get();
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws NotFoundException {
        Optional<Product> productDB = productRepository.findById(productId);

        if(productDB.isEmpty()){
            throw new NotFoundException("Product with the given " + productId + " is not founded!");
        }


        if(Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product
                .getProductName())){
            productDB.get().setProductName(product.getProductName());
        }

        if(product.getProductPrice() != 0.0){
            productDB.get().setProductPrice(product.getProductPrice());
        }


        if(product.getProductQuantity() > 0 && product.getProductQuantity() < 11){
            productDB.get().setProductQuantity(product.getProductQuantity());
        }

        if(Objects.nonNull(product.getProductDetails()) && !"".equalsIgnoreCase(product
                .getProductDetails())){
            productDB.get().setProductDetails(product.getProductDetails());
        }


        return productRepository.save(productDB.get());
    }

    @Override
    public String deleteProduct(Long productId) throws NotFoundException {

        Optional<Product> productDB = productRepository.findById(productId);

        if(productDB.isEmpty()){
            throw new NotFoundException("Product with the given " + productId + " is not founded!");
        }
        productRepository.delete(productDB.get());

        return "Product with the given " + productId + " deleted successfully!";
    }

    @Override
    public Product updateProductQuantity(Long productId, int productQuantity) throws NotFoundException {
        Optional<Product> product = productRepository.findByProductId(productId);

        if(product.isPresent()){
            product.get().setProductQuantity(productQuantity);
            productRepository.save(product.get());
        }else{
            throw new NotFoundException("Product with id " + productId + " not found!");
        }

        return product.get();
    }


}
