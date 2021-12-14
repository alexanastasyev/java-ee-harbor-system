package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    /**
     * Search all products
     * @return List of Products and empty list if there are no products
     */
    List<Product> findAll();

    /**
     * Searches for all products currently in harbour
     * @return List of Products and empty list if there are no products
     */
    List<Product> findAllInHarbour();

    /**
     * Search product by id
     * @param id identifier of product
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Product> findById(Long id);

    /**
     * Save new product
     * @param product saving product
     */
    void save(Product product);

    /**
     * Update existing product
     * @param product updating product
     */
    void update(Product product);
}
