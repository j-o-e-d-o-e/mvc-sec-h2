package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Address;
import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.services.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private IService<User> userService;
    private IService<Product> productService;

    public UserController(IService<User> userService, IService<Product> productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "registration-form";
    }

    @GetMapping("/account")
    public String account(Model model, Principal principal) {
        User user = userService.getByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddresses().get(0));
        return "registration-form";
    }

    @PostMapping("/registration-done")
    public String registrationDone(@Valid User user, BindingResult resultUser, @Valid Address address, BindingResult resultAddress) {
        if (resultUser.hasErrors() || resultAddress.hasErrors()) {
            return "registration-form";
        }
        user.addAddress(address);
        if (user.getId() != null) {
            user.setUpdate(true);
        }
        userService.saveOrUpdate(user);
        return "registration-done";
    }

    @GetMapping("/added-to-cart/{id}")
    public String addedToCart(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getById(id);
        User user = userService.getByName(principal.getName());
        user.addProduct(product);
        userService.saveOrUpdate(user);
        model.addAttribute("name", product.getName());
        return "added-to-cart";
    }

    @GetMapping("shopping-cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.getByName(principal.getName());
        model.addAttribute("products", user.getProducts());
        model.addAttribute("sum", user.sum());
        return "shopping-cart";
    }

    @GetMapping("order-done")
    public String orderDone(Model model, Principal principal) {
        User user = userService.getByName(principal.getName());
        user.removeAllProducts();
        userService.saveOrUpdate(user);
        model.addAttribute("address", user.getAddresses().get(0));
        return "order-done";
    }
}