package com.onlineshop.productmicroservice.core.app.service.interfaces;


import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;
import com.onlineshop.productmicroservice.port.user.exception.EmptyFieldException;

public interface IAppService {

    String sendProductToCart(SendMessageToCartDto productAndUserInfo) throws EmptyFieldException;
}
