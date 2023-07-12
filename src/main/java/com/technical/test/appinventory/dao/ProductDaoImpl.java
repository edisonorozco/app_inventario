package com.technical.test.appinventory.dao;

import com.technical.test.appinventory.dao.model.Product;
import com.technical.test.appinventory.entities.ProductEntity;
import com.technical.test.appinventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return StreamSupport.stream(productRepository
                                .findAll()
                                .spliterator(),
                        false)
                .toList()
                .stream()
                .map(productEntity -> Product.builder()
                        .id(productEntity.getId())
                        .name(productEntity.getName())
                        .description(productEntity.getDescription())
                        .purchaseDate(productEntity.getPurchaseDate())
                        .purchaseValue(productEntity.getPurchaseValue())
                        .depreciationRate(productEntity.getDepreciationRate())
                        .currentValue(productEntity.getCurrentValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {

        ProductEntity productEntity = productRepository.save(ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .purchaseDate(product.getPurchaseDate())
                .purchaseValue(product.getPurchaseValue())
                .depreciationRate(product.getDepreciationRate())
                .currentValue(product.getPurchaseValue())
                .build());

        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .purchaseDate(productEntity.getPurchaseDate())
                .purchaseValue(productEntity.getPurchaseValue())
                .depreciationRate(productEntity.getDepreciationRate())
                .currentValue(productEntity.getCurrentValue())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void performDepreciation() {
        List<ProductEntity> productEntityList = StreamSupport.stream(
                        productRepository.findAll()
                                .spliterator(),
                        false)
                .toList();

        LocalDate currentDate = LocalDate.now();

        for (ProductEntity productEntity : productEntityList) {
            LocalDate purchaseDate = productEntity.getPurchaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsSincePurchase = ChronoUnit.YEARS.between(purchaseDate, currentDate);

            double depreciationPercentage = productEntity.getDepreciationRate() / 100.0;
            double depreciationAmount = productEntity.getPurchaseValue() * depreciationPercentage * yearsSincePurchase;

            double currentValue = productEntity.getPurchaseValue() - depreciationAmount;
            productEntity.setCurrentValue(currentValue);

            productRepository.save(productEntity);
        }

    }

}
