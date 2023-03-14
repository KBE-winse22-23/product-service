package com.onlineshop.productmicroservice.core.domain.service.interfaces;

import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.port.user.exception.AlreadyExistsException;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product saveProduct(Product product) throws AlreadyExistsException;

    Product getProductById(Long id) throws NotFoundException;

    Product updateProduct(Long productId, Product product) throws NotFoundException;


    String deleteProduct(Long productId) throws NotFoundException;

    Product updateProductQuantity(Long productId, int productQuantity) throws NotFoundException;
}
