package com.example.tienda.service;

import com.example.tienda.model.Product;
import com.example.tienda.model.ProductType;
import com.example.tienda.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product findByName (String name){return productRepository.findByName(name);}
    public List<Product> findAllByProductType(ProductType productType){return productRepository.findAllByProductType(productType);}
    public void createProduct(Product product){
        productRepository.save(product);
    }
}
