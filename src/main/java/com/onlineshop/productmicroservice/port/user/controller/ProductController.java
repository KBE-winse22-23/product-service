package com.onlineshop.productmicroservice.port.user.controller;

import com.onlineshop.productmicroservice.core.app.dto.SendMessageToCartDto;
import com.onlineshop.productmicroservice.core.app.service.interfaces.IAppService;
import com.onlineshop.productmicroservice.core.domain.model.CustomMessage;
import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductService;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;
import com.onlineshop.productmicroservice.port.user.producer.ProductProducer;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IAppService appService;

    @Autowired
    private ProductProducer productProducer;

    @Operation(
            tags = {"Product"},
            operationId = "getAllProducts",
            summary = "Fetches all the Products from the Database",
            description = "This End Point gets all the Products from the Database and return it as JSON Objects"
    )
    @GetMapping()
    public List<Product> fetchProducts(){
        return productService.getProducts();
    }

    @Operation(
            tags = {"Product"},
            operationId = "saveProduct",
            summary = "Saves a New Product to the Database",
            description = "This End Point takes a Product Object as a Request Body and Saves it in the Database"
    )
    @PostMapping()
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @Operation(
            tags = {"Product"},
            operationId = "getProductById",
            summary = "Fetches a Product by its id from the Database",
            description = "This End Point gets a Product by its id from the Database. It takes the product id as path variable e.g. product/id"
    )
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @Operation(
            tags = {"Product"},
            operationId = "updateProduct",
            summary = "Updates an already existing Product in the Database",
            description = "This End Point updates a Product in the Database. The Product id is required as path variable and the new Product Object is required is request body." +
                    "It will throw an IllegalArgumentException if the Product with given id doesn't exist."
    )
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) throws NotFoundException {
        return productService.updateProduct(productId, product);
    }

    @Operation(
            tags = {"Product"},
            operationId = "deleteProduct",
            summary = "Deletes a Product from the Database",
            description = "This End Point deletes a Product with the given id in the Database. The Product id is required as path variable." +
                    "It will throw an NotFoundException if the Product with given id doesn't exist."
    )
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        return productService.deleteProduct(productId);
    }


    ///////////////////////////////////////////////////////////
    ///////////////////// PRODUCER ////////////////////////////
    ///////////////////////////////////////////////////////////
    @Operation(
            tags = {"Product Message Producer"}
    )
    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message){
        return productProducer.publishMessage(message);
    }

    @Operation(
            tags = {"Product Message Producer"}
    )
    @PostMapping("/send-product-to-cart")
    public String sendProductToCart(@RequestBody SendMessageToCartDto productAndUserInfo){
        return appService.sendProductToCart(productAndUserInfo);
    }
}
