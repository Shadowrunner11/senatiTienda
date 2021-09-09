package com.example.tienda.repository;

import com.example.tienda.model.Product;
import com.example.tienda.model.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    Product findByName(String name);



}
