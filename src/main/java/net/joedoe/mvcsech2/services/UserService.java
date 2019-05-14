package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.repositories.IRoleRepository;
import net.joedoe.mvcsech2.repositories.IUserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService, IService<User> {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(11);
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.addRole(roleRepository.findByName("ROLE_USER").orElse(null));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if (user.isUpdate()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getRoles());
            user.setProducts(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getProducts());
            user.setUpdate(false);
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Converter<User, UserDetails> converter =
                user -> {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
                    return new CustomUserDetails(authorities, user.getUsername(), user.getPassword(), true);
                };
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return converter.convert(user);
    }
}
