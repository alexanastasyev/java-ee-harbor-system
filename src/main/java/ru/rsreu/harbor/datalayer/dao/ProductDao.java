package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll();

    List<Product> findAllInHarbour();

    Optional<Product> findById(Long id);

    void save(Product product);

    void update(Product product);
}
