package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.Role;
import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService, IService<User> {
    private IUserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getByName(String name) {
        return repository.findByUsername(name);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.addRole(new Role("ROLE_USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if (user.isUpdate()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Objects.requireNonNull(repository.findById(user.getId()).orElse(null)).getRoles());
            user.setProducts(Objects.requireNonNull(repository.findById(user.getId()).orElse(null)).getProducts());
            user.setUpdate(false);
        }
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Converter<User, UserDetails> converter =
                user -> {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
                    return new CustomUserDetails(authorities, user.getUsername(), user.getPassword(), true);
                };
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return converter.convert(user);
    }
}
