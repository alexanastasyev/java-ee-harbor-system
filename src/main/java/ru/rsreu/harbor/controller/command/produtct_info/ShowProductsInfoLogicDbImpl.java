package ru.rsreu.harbor.controller.command.produtct_info;

import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public class ShowProductsInfoLogicDbImpl implements ShowProductsInfoLogic {
    private final ProductDao productDao;

    public ShowProductsInfoLogicDbImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productDao.findAll();
    }
}