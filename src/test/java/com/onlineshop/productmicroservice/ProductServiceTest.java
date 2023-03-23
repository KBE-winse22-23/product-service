package com.onlineshop.productmicroservice;

import com.onlineshop.productmicroservice.core.domain.model.Product;
import com.onlineshop.productmicroservice.core.domain.service.impl.ProductService;
import com.onlineshop.productmicroservice.core.domain.service.interfaces.IProductRepository;
import com.onlineshop.productmicroservice.port.user.exception.AlreadyExistsException;
import com.onlineshop.productmicroservice.port.user.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {
    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private static final Long PRODUCT_ID = 1L;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(PRODUCT_ID);
        product.setProductName("Test Product");
        product.setProductPrice(100.0);
        product.setProductQuantity(10);
    }

    @Test
    public void testGetProducts() {
        // Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, 1234L, "Product 1", "Product details 1", 10.0, "image1.jpg", 4, 20));
        productList.add(new Product(2L, 5678L, "Product 2", "Product details 2", 15.0, "image2.jpg", 3, 30));
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> result = productService.getProducts();

        // Then
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getProductName());
        assertEquals("Product details 2", result.get(1).getProductDetails());
    }

    @Test
    public void testSaveProductWithUniqueProductId() throws AlreadyExistsException {
        // Given
        Product product = new Product(1L, 1234L, "Product 1", "Product details 1", 10.0, "image1.jpg", 4, 20);
        when(productRepository.findByProductId(product.getProductId())).thenReturn(Optional.empty());
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product result = productService.saveProduct(product);

        // Then
        assertEquals(product, result);
    }

    @Test
    public void testGetProductById() throws NotFoundException {
        // Part 1: given
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setProductId(productId);
        product.setProductName("Product1");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Part 2: when
        Product foundProduct = productService.getProductById(productId);

        // Part 3: then
        assertEquals(product, foundProduct);
    }
     @Test
    public void testSaveProduct() throws AlreadyExistsException {
        // Part 1: given
        Product product = new Product();
        product.setId(1L);
        product.setProductId(1L);
        product.setProductName("Product1");

        when(productRepository.findByProductId(any())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(product);

        // Part 2: when
        Product savedProduct = productService.saveProduct(product);

        // Part 3: then
        assertEquals(product, savedProduct);
    }

    @Test
    public void testUpdateProduct() throws NotFoundException {
        // Part 1: given
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setProductId(productId);
        product.setProductName("Product1");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Product2");
        updatedProduct.setProductPrice(20.0);
        updatedProduct.setProductQuantity(5);
        updatedProduct.setProductDetails("New product details");

        // Part 2: when
        Product returnedProduct = productService.updateProduct(productId, updatedProduct);

        // Part 3: then
        assertEquals(updatedProduct.getProductName(), returnedProduct.getProductName());
        assertEquals(updatedProduct.getProductPrice(), returnedProduct.getProductPrice());
        assertEquals(updatedProduct.getProductQuantity(), returnedProduct.getProductQuantity());
        assertEquals(updatedProduct.getProductDetails(), returnedProduct.getProductDetails());
    }

    @Test
    public void testDeleteProduct() throws NotFoundException {
        // Part 1: given
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setProductId(productId);
        product.setProductName("Product1");

        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));

        // Part 2: when
        String result = productService.deleteProduct(productId);

        // Part 3: then
        assertEquals("Product with the given 1 deleted successfully!", result);
    }

    @Test
    public void testUpdateProductQuantity() throws NotFoundException {
        // Part 1: given
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setProductId(productId);
        product.setProductName("Product1");
        product.setProductQuantity(10);

        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        int newQuantity = 5;

        // Part 2: when
        Product returnedProduct = productService.updateProductQuantity(productId, newQuantity);

        // Part 3: then
        assertNotNull(returnedProduct);
        assertEquals(productId, returnedProduct.getProductId());
        assertEquals(newQuantity, returnedProduct.getProductQuantity());
        verify(productRepository, times(1)).findByProductId(productId);
        verify(productRepository, times(1)).save(any(Product.class));
}
}