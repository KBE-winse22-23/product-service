package com.onlineshop.productmicroservice.core.app.service.impl;


import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;
import com.onlineshop.productmicroservice.core.app.service.interfaces.IAppService;
import com.onlineshop.productmicroservice.port.user.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService implements IAppService {

    @Autowired
    private ProductProducer productProducer;

    @Override
    public String sendProductToCart(SendMessageToCartDto productAndUserInfo) {
        return productProducer.sendProductAndUserInfoToCart(productAndUserInfo);
    }
}
