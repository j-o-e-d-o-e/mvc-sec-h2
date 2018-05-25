package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.Product;
import net.joedoe.mvcsech2.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService implements IService<Product> {
    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> listAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Product getByName(String name) {
        return null;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
