package com.technical.test.appinventory.dao;

import com.technical.test.appinventory.dao.model.Product;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    void performDepreciation();
}
