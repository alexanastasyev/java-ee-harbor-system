package ru.rsreu.harbor.controller.command.captain.upload;

import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public class UploadCommandLogicDbImpl implements UploadCommandLogic {
    private final ProductDao productDao;

    public UploadCommandLogicDbImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void upload(List<Long> productIds) {
        productIds.forEach(id -> {
            Product product = this.productDao.findById(id).orElseThrow(IllegalArgumentException::new);
            this.productDao.update(product);
        });
    }
}
