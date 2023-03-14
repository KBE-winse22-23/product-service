package com.onlineshop.productmicroservice.core.app.service.impl;


import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;
import com.onlineshop.productmicroservice.core.app.service.interfaces.IAppService;
import com.onlineshop.productmicroservice.port.user.exception.EmptyFieldException;
import com.onlineshop.productmicroservice.port.user.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService implements IAppService {

    @Autowired
    private ProductProducer productProducer;

    @Override
    public String sendProductToCart(SendMessageToCartDto productAndUserInfo) throws EmptyFieldException {
        if(productAndUserInfo.getProductId()==null || productAndUserInfo.getProductName().equals("")
            || productAndUserInfo.getProductPrice()==0 || productAndUserInfo.getEmail().equals("") ||
                productAndUserInfo.getOwnerFirstName().equals("") ||  productAndUserInfo.getOwnerLastName().equals("")
                || productAndUserInfo.getImage().equals("")
        ){
            throw new EmptyFieldException("All the required fields must be filled in!");
        }
        return productProducer.sendProductAndUserInfoToCart(productAndUserInfo);
    }
}
