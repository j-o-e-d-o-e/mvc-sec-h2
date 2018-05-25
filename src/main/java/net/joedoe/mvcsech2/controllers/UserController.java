package net.joedoe.mvcsech2.controllers;

import net.joedoe.mvcsech2.domains.Address;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

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

    @PostMapping("/registration-done")
    public String registrationDone(@Valid User user, BindingResult resultUser, @Valid Address address, BindingResult resultAddress) {
        if (resultUser.hasErrors() || resultAddress.hasErrors()) {
            return "registration-form";
        }
        user.addAddress(address);
        userService.saveOrUpdate(user);
        return "registration-done";
    }

    @GetMapping("/account")
    public String account(Model model, Principal principal) {
        User user = userService.getByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddresses().get(0));
        return "registration-form";
    }
}
