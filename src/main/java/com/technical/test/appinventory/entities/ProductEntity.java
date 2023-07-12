package com.technical.test.appinventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_productos")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "nombre")
    private String name;
    @Column(name = "fecha_compra")
    private Date purchaseDate;
    @Column(name = "valor_compra")
    private double purchaseValue;
    @Column(name = "depreciacion")
    private double depreciationRate;
    @Column(name = "valor_actual")
    private double currentValue;

}
