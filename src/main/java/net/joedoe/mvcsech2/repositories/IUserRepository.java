package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
