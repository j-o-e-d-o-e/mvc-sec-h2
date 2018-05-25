package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService, IService<User> {
    @Autowired
    private IUserRepository repository;

    @Override
    public List<User> listAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public User getByName(String name) {
        return repository.findByUsername(name);
    }

    @Override
    public User saveOrUpdate(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Converter<User, UserDetails> converter =
                user -> {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role.getRole()));
                    });
                    return new CustomUserDetails(authorities, user.getUsername(), user.getPassword(), true);
                };
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return converter.convert(user);
    }
}
