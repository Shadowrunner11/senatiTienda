package com.example.tienda.util;

import com.example.tienda.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoCompras {
    private int cantidad;
    private Product product;
}
