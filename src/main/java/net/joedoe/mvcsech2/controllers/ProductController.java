package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<Product> products = productService.listAll();
        System.out.println(products.toString());
        model.addAttribute("products", productService.listAll());
        return "home";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        System.out.println(product.getTitle());
        model.addAttribute("product", productService.getById(id));
        return "product";
    }

    @GetMapping("/order-done/{title}")
    public String orderDone(@PathVariable String title, Model model){
        model.addAttribute("title", title);
        return "order-done";
    }
}
