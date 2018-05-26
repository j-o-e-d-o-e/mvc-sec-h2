package net.joedoe.mvcsech2.configs;

import net.joedoe.mvcsech2.domains.Address;
import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.domains.Role;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootstrapDataConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IService productService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadProducts();
    }

    private void loadUsers() {
        User user1 = new User("Mary", "Jane", "mary", passwordEncoder.encode("jane"));
        user1.addRole(new Role("ROLE_USER"));
        user1.addAddress(new Address("Main Ave 666", "54321", "Metropolis"));
        userService.saveOrUpdate(user1);

        User user2 = new User("Joe", "Doe", "joe", passwordEncoder.encode("doe"));
        user2.setRoles(Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_ADMIN")));
        user2.addAddress(new Address("Bourbon St 3", "12345", "Smallville"));
        user2.addAddress(new Address("Main Ave 666", "54321", "Metropolis"));
        userService.saveOrUpdate(user2);
    }

    private void loadProducts() {
        Product product1 = new Product("Beer", "A tasty pint", "beer", "#FF6766", 4.00);
        productService.saveOrUpdate(product1);

        Product product2 = new Product("Laptop", "For work and leisure", "laptop", "#97CE68", 1499.95);
        productService.saveOrUpdate(product2);

        Product product3 = new Product("Headphones", "Quiet comfort", "headphones", "#4BA6E0", 249.95);
        productService.saveOrUpdate(product3);

        Product product4 = new Product("Concert", "2x concert tickets", "music", "#ffb366", 79.90);
        productService.saveOrUpdate(product4);

        Product product5 = new Product("Bicycle", "Two wheels, one saddle", "bicycle", "green", 889.95);
        productService.saveOrUpdate(product5);

        Product product6 = new Product("Cinema", "2x movie tickets", "film", "dimgray", 20.00);
        productService.saveOrUpdate(product6);
    }
}