package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Product;

import java.util.Optional;

public interface ProductDao {
    Optional<Product> findById(Long id);
}
