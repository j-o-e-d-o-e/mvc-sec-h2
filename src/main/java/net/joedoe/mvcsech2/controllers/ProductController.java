package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("products", productService.listAll());
        return "home";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }

    @GetMapping("/order-done/{title}")
    public String orderDone(@PathVariable String title, Model model){
        model.addAttribute("title", title);
        return "order-done";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/product-added")
    public String registrationDone(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product";
        }
        product.setIcon("fas fa-square fa-7x");
        product.setColor("color: " + product.getColor());
        productService.saveOrUpdate(product);
        return "product-added";
    }
}
