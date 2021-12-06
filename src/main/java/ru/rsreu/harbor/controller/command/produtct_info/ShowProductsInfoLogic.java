package ru.rsreu.harbor.controller.command.produtct_info;

import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public interface ShowProductsInfoLogic {
    List<Product> getAllProducts();
}
