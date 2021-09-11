package com.example.tienda.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rb_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer stock;

    @Column
    private String description;

    @Column
    private String imagenLink;
}
