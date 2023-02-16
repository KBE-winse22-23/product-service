package com.onlineshop.productmicroservice.core.app.service.interfaces;


import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;

public interface IAppService {

    String sendProductToCart(SendMessageToCartDto productAndUserInfo);
}
