package com.onlineshop.productmicroservice;

import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.impl.ProductService;
import com.onlineshop.productmicroservice.port.user.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class IntegrationTest
{
    @Autowired
    private MockMvc mvc;

    @Test
    void getProductById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
