package net.joedoe.mvcsech2.configs;

import net.joedoe.mvcsech2.domains.Role;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IService userService;
    @Autowired
    private IService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("joe");
        user1.setPassword(passwordEncoder.encode("doe"));
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("mary");
        user2.setPassword(passwordEncoder.encode("jane"));
        userService.saveOrUpdate(user2);
    }

    private void loadRoles() {
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        roleService.saveOrUpdate(userRole);

        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        roleService.saveOrUpdate(adminRole);
    }

    private void assignUsersToUserRole() {
        List<Role> roles = roleService.listAll();
        List<User> users = userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ROLE_USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("joe") || user.getUsername().equals("mary")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = roleService.listAll();
        List<User> users = userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("joe")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}