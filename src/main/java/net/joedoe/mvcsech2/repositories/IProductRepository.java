package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
}
