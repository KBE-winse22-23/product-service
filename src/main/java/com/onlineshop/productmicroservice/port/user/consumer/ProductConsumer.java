package com.onlineshop.productmicroservice.port.user.consumer;


import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductService;
import com.onlineshop.productmicroservice.port.config.MQConfig;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = MQConfig.PRODUCT_QUEUE)
    public void addProductListener(Product product){

        Product prod = new Product();
        prod.setProductQuantity(product.getProductQuantity());
        prod.setProductId(product.getProductId());
        prod.setProductDetails(product.getProductDetails());
        prod.setProductName(product.getProductName());
        prod.setProductPrice(product.getProductPrice());
        prod.setProductImage(product.getProductImage());
        productService.saveProduct(prod);
    }

    @RabbitListener(queues = MQConfig.PRODUCT_UPDATE_QUEUE)
    public void updateProductQuantityListener(Product product) throws NotFoundException {
      Product prod = new Product();
      prod.setProductId(product.getProductId());
      prod.setProductQuantity(product.getProductQuantity());
        System.out.println(prod);
        productService.updateProductQuantity(prod.getProductId(), prod.getProductQuantity());

    }
}
