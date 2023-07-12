package com.technical.test.appinventory.controller;

import com.technical.test.appinventory.dao.ProductDao;
import com.technical.test.appinventory.dao.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ApiRestProductTest {
    @Mock
    private ProductDao productDao;

    private ApiRestProduct apiRestProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiRestProduct = new ApiRestProduct(productDao);
    }

    @Test
    void listProducts_shouldReturnListOfProducts() {
        // Arrange
        Product product1 = Product.builder()
                .id(1L)
                .description("Description 1")
                .name("Product 1")
                .purchaseDate(null)
                .purchaseValue(100.0)
                .depreciationRate(0.0)
                .currentValue(0.0)
                .build();
        Product product2 = Product.builder()
                .id(2L)
                .description("Description 2")
                .name("Product 2")
                .purchaseDate(null)
                .purchaseValue(100.0)
                .depreciationRate(0.0)
                .currentValue(0.0)
                .build();

        List<Product> products = Arrays.asList(product1, product2);

        when(productDao.getAllProducts()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = apiRestProduct.listProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    void saveProduct_shouldSaveProduct() {
        // Arrange
        Product product = Product.builder()
                .id(1L)
                .description("Description 1")
                .name("Product 1")
                .purchaseDate(null)
                .purchaseValue(100.0)
                .depreciationRate(0.0)
                .currentValue(0.0)
                .build();

        when(productDao.saveProduct(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = apiRestProduct.saveProduct(product);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productDao, times(1)).saveProduct(product);
    }

    @Test
    void updateProduct_withValidProduct_shouldUpdateProduct() {
        // Arrange
        Product product = Product.builder()
                .id(1L)
                .description("Description 1")
                .name("Product 1")
                .purchaseDate(null)
                .purchaseValue(100.0)
                .depreciationRate(0.0)
                .currentValue(0.0)
                .build();

        when(productDao.saveProduct(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = apiRestProduct.updateProduct(product);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productDao, times(1)).saveProduct(product);
    }

    @Test
    void updateProduct_withMissingId_shouldThrowException() {
        // Arrange
        Product product = Product.builder()
                .id(null)
                .description("Description 1")
                .name("Product 1")
                .purchaseDate(null)
                .purchaseValue(100.0)
                .depreciationRate(0.0)
                .currentValue(0.0)
                .build();;

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            apiRestProduct.updateProduct(product);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("El campo 'id' es obligatorio", exception.getReason());
        verify(productDao, never()).saveProduct(any(Product.class));
    }

    @Test
    void deleteProduct_shouldDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<String> response = apiRestProduct.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro eliminado con exito!", response.getBody());
        verify(productDao, times(1)).deleteProduct(productId);
    }
}