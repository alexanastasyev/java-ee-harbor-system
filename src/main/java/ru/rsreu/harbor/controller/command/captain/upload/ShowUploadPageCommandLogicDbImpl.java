package ru.rsreu.harbor.controller.command.captain.upload;

import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public class ShowUploadPageCommandLogicDbImpl implements ShowUploadPageCommandLogic {
    private final ProductDao productDao;

    public ShowUploadPageCommandLogicDbImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAllInHarbour();
    }
}
