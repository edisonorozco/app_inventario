package com.technical.test.appinventory.dao;

import org.junit.jupiter.api.Test;

import com.technical.test.appinventory.dao.model.Product;
import com.technical.test.appinventory.entities.ProductEntity;
import com.technical.test.appinventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductDaoImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductDaoImpl productDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDao = new ProductDaoImpl(productRepository);
    }

    @Test
    void getAllProducts_shouldReturnAllProducts() {
        // Arrange
        ProductEntity productEntity1 = new ProductEntity(1L, "Product 1", "Description 1",
                new Date(), 100.0, 5.0, 100.0);
        ProductEntity productEntity2 = new ProductEntity(2L, "Product 2", "Description 2",
                new Date(), 200.0, 10.0, 200.0);
        List<ProductEntity> productEntities = Arrays.asList(productEntity1, productEntity2);

        when(productRepository.findAll()).thenReturn(productEntities);

        // Act
        List<Product> products = productDao.getAllProducts();

        // Assert
        assertEquals(2, products.size());
        assertEquals("Description 1", products.get(0).getName());
        assertEquals("Description 2", products.get(1).getName());
    }

    @Test
    void saveProduct_shouldSaveProductAndReturnSavedProduct() {
        // Arrange
        Product product = Product.builder()
                .description("1")
                .name("1")
                .id(1L)
                .purchaseDate(new Date())
                .currentValue(100.0)
                .purchaseValue(100.0)
                .depreciationRate(4.0)
                .build();
        ProductEntity savedProductEntity = new ProductEntity(1L, "Product 1", "Description 1",
                new Date(), 100.0, 5.0, 100.0);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedProductEntity);

        // Act
        Product savedProduct = productDao.saveProduct(product);

        // Assert
        assertEquals("Description 1", savedProduct.getName());
        assertEquals(100.0, savedProduct.getCurrentValue());
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void deleteProduct_shouldCallProductRepositoryDeleteById() {
        // Arrange
        Long productId = 1L;

        // Act
        productDao.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void performDepreciation_shouldUpdateCurrentValueForAllProducts() {
        // Arrange
        LocalDate purchaseDate = LocalDate.now().minusYears(2);
        ProductEntity productEntity1 = new ProductEntity(1L, "Product 1", "Description 1",
                toDate(purchaseDate), 100.0, 5.0, 0.0);
        ProductEntity productEntity2 = new ProductEntity(2L, "Product 2", "Description 2",
                toDate(purchaseDate), 200.0, 10.0, 0.0);
        List<ProductEntity> productEntities = Arrays.asList(productEntity1, productEntity2);

        when(productRepository.findAll()).thenReturn(productEntities);

        // Act
        productDao.performDepreciation();

        // Assert
        verify(productRepository, times(2)).save(any(ProductEntity.class));
        assertEquals(90.0, productEntity1.getCurrentValue());
        assertEquals(160.0, productEntity2.getCurrentValue());
    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(TimeZone.getDefault().toZoneId()).toInstant());
    }
}