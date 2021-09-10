package com.example.tienda.controller;

import com.example.tienda.model.Product;
import com.example.tienda.model.ProductType;
import com.example.tienda.service.ProductService;
import com.example.tienda.util.CarritoCompras;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    List<CarritoCompras> carrito = new ArrayList<>();
    List<Long> ids = new ArrayList<>();

   @GetMapping("/")
   public String index(Model model){
       List<Product> list = new ArrayList<>();
       /*productService.createProduct(new Product(null, ProductType.AUDIO, "Tampones", 12.56, 10,"Son tampones"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Audifonos 3M", 13.56, 10,"xds"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Orejeras NoiceCancek", 20.6, 20,"Son xd"));
       productService.createProduct(new Product(null, ProductType.FACIAL, "Facial Shield", 12.56, 10,"Son xdss"));*/
       productService.getAllProducts().forEach(list::add);


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
   @GetMapping("/detailProduct/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model){
       if (id!=null) {
           model.addAttribute("product", productService.findById(id));
       }

       return "detailProduct";
   }
   @GetMapping("/carrito")
    public String showCarrito(@RequestParam String cantidad, @RequestParam String id, Model model){
        int n_cantidad = Integer.parseInt(cantidad);
        Long n_id = Long.parseLong(id);
        if(!ids.contains(n_id)){
            ids.add(n_id);
            carrito.add(new CarritoCompras(n_cantidad,productService.findById(n_id)));
        };
        model.addAttribute("listaCarrito", carrito);
        return "carrito";
   }

}
