package com.example.tienda.controller;

import com.example.tienda.model.Product;
import com.example.tienda.model.ProductType;
import com.example.tienda.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

   @GetMapping("/")
   public String index(Model model){
       List<Product> list = new ArrayList<>();
       /*productService.createProduct(new Product(null, ProductType.AUDIO, "Tampones", 12.56, 10,"Son tampones"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Audifonos 3M", 13.56, 10,"xds"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Orejeras NoiceCancek", 20.6, 20,"Son xd"));
       productService.createProduct(new Product(null, ProductType.FACIAL, "Facial Shield", 12.56, 10,"Son xdss"));*/
       productService.getAllProducts().forEach(list::add);
       List<Product> carrito = new ArrayList<>();
       carrito.add(productService.findByName("Tampones"));
       model.addAttribute("products", list);
       model.addAttribute("carrito", carrito);
       return "productos";
   }

   @GetMapping("/productos/{category}")
    public String showCategory(@PathVariable("category") ProductType productType, Model model){
        List<Product> list =productService.findAllByProductType(productType);
        model.addAttribute("products", list);
        return "productos";
   }

}
