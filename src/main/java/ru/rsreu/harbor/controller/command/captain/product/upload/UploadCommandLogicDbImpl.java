package ru.rsreu.harbor.controller.command.captain.product.upload;

import ru.rsreu.harbor.controller.command.captain.product.ProductsActionsLogicDbImpl;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public class UploadCommandLogicDbImpl extends ProductsActionsLogicDbImpl implements UploadCommandLogic {
    private final ProductDao productDao;

    public UploadCommandLogicDbImpl(
            ProductDao productDao,
            UserDao userDao,
            PierAssignmentDao pierAssignmentDao,
            RequestStatusDao requestStatusDao) {
        super(userDao, pierAssignmentDao, requestStatusDao);
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
