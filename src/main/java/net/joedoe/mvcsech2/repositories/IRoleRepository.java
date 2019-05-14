package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
