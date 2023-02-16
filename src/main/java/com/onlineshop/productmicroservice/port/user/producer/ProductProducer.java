package com.onlineshop.productmicroservice.port.user.producer;

import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;
import com.onlineshop.productmicroservice.core.domain.model.CustomMessage;
import com.onlineshop.productmicroservice.port.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {
    @Autowired
    private RabbitTemplate template;

    public String publishMessage(CustomMessage message) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.CART_ROUTING_KEY,message);

        return "Message Published";
    }

    public String sendProductAndUserInfoToCart(SendMessageToCartDto productAndUserInfo) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.CART_ROUTING_KEY,productAndUserInfo);

        return "Product and User information has been sent to Cart-Service";
    }
}
