package net.joedoe.mvcsech2.configs;

import net.joedoe.mvcsech2.domains.Address;
import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.domains.Role;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.services.IService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootstrapDataConfig implements ApplicationListener<ContextRefreshedEvent> {
    private IService<User> userService;
    private IService<Product> productService;

    public BootstrapDataConfig(IService<User> userService, IService<Product> productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadProducts();
    }

    private void loadUsers() {
        User user1 = new User("Mary", "Jane", "mary", "jane");
        user1.addAddress(new Address("Main Ave 666", "54321", "Metropolis"));
        userService.saveOrUpdate(user1);

        User user2 = new User("Joe", "Doe", "joe", "doe");
        user2.addAddress(new Address("Bourbon St 3", "12345", "Seville"));
        user2.addAddress(new Address("Main Ave 666", "54321", "Metropolis"));
        user2.addRole(new Role("ROLE_ADMIN"));
        userService.saveOrUpdate(user2);
    }

    private void loadProducts() {
        Product product1 = new Product("Phone", "A telephone", "phone", "#FF6766", 49.99);
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

        Product product7 = new Product("Magnet", "Versatile", "magnet", "#FF6766", 9.95);
        productService.saveOrUpdate(product7);
    }
}