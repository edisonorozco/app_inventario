package com.technical.test.appinventory.controller;

import com.technical.test.appinventory.dao.ProductDao;
import com.technical.test.appinventory.dao.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product-information")
@RequiredArgsConstructor
public class ApiRestProduct {

    private final ProductDao productDao;

    @GetMapping(path = "/list-products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok().body(productDao.getAllProducts());
    }

    @PostMapping(path = "/save-product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productDao.saveProduct(product));
    }

    @PostMapping(path = "/update-product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        if (product.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo 'id' es obligatorio");
        }

        return ResponseEntity.ok().body(productDao.saveProduct(product));
    }

    @DeleteMapping(path = "/delete-product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        productDao.deleteProduct(id);
        return ResponseEntity.ok().body("Registro eliminado con exito!");
    }
}
