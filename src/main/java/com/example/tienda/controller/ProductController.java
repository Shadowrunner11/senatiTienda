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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;
    private static DecimalFormat decima = new DecimalFormat("0.00");
    private static String importeDecimal = "0.00";
    List<CarritoCompras> carrito = new ArrayList<>();
    List<Long> ids = new ArrayList<>();

    @GetMapping("/inicio")
    public String inicio(Model model){

        return "inicio";
    }

   @GetMapping("/")
   public String index(Model model){
       List<Product> list = new ArrayList<>();
       /*
       productService.createProduct(new Product(null, ProductType.AUDIO, "Tapones", 12.56, 10,"Tapones antiruido de alta calidad","https://sodimac.scene7.com/is/image/SodimacPeru/1070061?fmt=jpg&fit=fit,1&wid=420&hei=420"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Orejeras 3M", 13.56, 10,"Orejeras que portejen hasta 50 dB","https://sodimac.scene7.com/is/image/SodimacPeru/3706753?fmt=jpg&fit=fit,1&wid=420&hei=420"));
       productService.createProduct(new Product(null, ProductType.AUDIO, "Orejeras NoiceCancel", 20.6, 20,"Orejaras profesional suprimen hasta 150dB","https://sodimac.scene7.com/is/image/SodimacPeru/1595245?fmt=jpg&fit=fit,1&wid=420&hei=420"));
       productService.createProduct(new Product(null, ProductType.FACIAL, "Facial Shield", 12.56, 10,"Protector facial de grafeno","https://sodimac.scene7.com/is/image/SodimacPeru/3935094?fmt=jpg&fit=fit,1&wid=420&hei=420"));
       */
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
   public String carrito(Model model){

       model.addAttribute("listaCarrito", carrito);
       model.addAttribute("importe", importeDecimal);
       return "carrito";
   }

   @PostMapping("/carrito")
    public String showCarrito(@RequestParam String cantidad, @RequestParam String id, Model model){
        int n_cantidad = Integer.parseInt(cantidad);
        Long n_id = Long.parseLong(id);
        Double precioT = productService.findById(n_id).getPrice() * n_cantidad;

        double importe = 0;

        if(!ids.contains(n_id)){
            ids.add(n_id);
            carrito.add(new CarritoCompras(n_cantidad,productService.findById(n_id), precioT));
            for (CarritoCompras product: carrito) {
                importe+=product.getPrecioT();
            }
            importeDecimal = decima.format(importe);
        };
        model.addAttribute("listaCarrito", carrito);
        model.addAttribute("importe", importeDecimal);
        return "carrito";
   }

}
