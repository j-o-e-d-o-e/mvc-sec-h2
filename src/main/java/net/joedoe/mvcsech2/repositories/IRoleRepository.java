package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
