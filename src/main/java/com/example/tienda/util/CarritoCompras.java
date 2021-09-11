package com.example.tienda.util;

import com.example.tienda.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoCompras {
    private int cantidad;
    private Product product;
    private Double precioT;
}
