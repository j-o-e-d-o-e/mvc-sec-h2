package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.services.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {
    private IService<Product> productService;

    public ProductController(IService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("products", productService.listAll());
        return "home";
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }


    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/product-added")
    public String registrationDone(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.saveOrUpdate(product);
        return "product-added";
    }
}
