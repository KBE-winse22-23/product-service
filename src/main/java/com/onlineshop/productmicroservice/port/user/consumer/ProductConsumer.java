package com.onlineshop.productmicroservice.port.user.consumer;


import com.onlineshop.productmicroservice.core.app.dto.DecreaseProductQuantityDto;
import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductService;
import com.onlineshop.productmicroservice.port.config.MQConfig;
import com.onlineshop.productmicroservice.port.user.exception.AlreadyExistsException;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConsumer {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductRepository productRepository;

    @RabbitListener(queues = MQConfig.PRODUCT_QUEUE)
    public void addProductListener(Product product) throws AlreadyExistsException {

        Product prod = new Product();
        prod.setProductQuantity(product.getProductQuantity());
        prod.setProductId(product.getProductId());
        prod.setProductDetails(product.getProductDetails());
        prod.setProductName(product.getProductName());
        prod.setProductPrice(product.getProductPrice());
        prod.setProductImage(product.getProductImage());
        productService.saveProduct(prod);
    }
//
//    @RabbitListener(queues = MQConfig.PRODUCT_UPDATE_QUEUE)
//    public void updateProductQuantityListener(Product product) throws NotFoundException {
//        productService.updateProductQuantity(product.getProductId(), product.getProductQuantity());
//
//    }

    @RabbitListener(queues = MQConfig.PRODUCT_UPDATE_QUEUE)
    public void decreaseProductQuantity(DecreaseProductQuantityDto product) throws NotFoundException {
        Optional<Product> productFromDB = productRepository.findByProductId(product.getProductId());
        if(productFromDB.isEmpty()){
            throw new NotFoundException("Product with the given id " + product.getProductId() + " not found!");
        }
        productService.updateProductQuantity(product.getProductId(), productFromDB.get().getProductQuantity() - product.getQuantity());
    }
}
