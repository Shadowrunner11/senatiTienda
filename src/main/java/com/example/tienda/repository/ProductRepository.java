package com.example.tienda.repository;

import com.example.tienda.model.Product;
import com.example.tienda.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByProductType(ProductType productType);

    Product findByName(String name);


}
