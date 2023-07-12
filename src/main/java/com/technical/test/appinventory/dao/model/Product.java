package com.technical.test.appinventory.dao.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {
    private Long id;
    private String description;
    private String name;
    private Date purchaseDate;
    private double purchaseValue;
    private double depreciationRate;
    private double currentValue;
}
