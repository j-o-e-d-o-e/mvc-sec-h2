package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
