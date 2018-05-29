package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IService<Product> {
    private IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> listAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product getByName(String name) {
        return null;
    }

    @Override
    public void saveOrUpdate(Product product) {
        repository.save(product);
    }
}
