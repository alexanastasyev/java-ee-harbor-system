package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Product;

public interface ProductDao {
    Product findById(Long id);
}
