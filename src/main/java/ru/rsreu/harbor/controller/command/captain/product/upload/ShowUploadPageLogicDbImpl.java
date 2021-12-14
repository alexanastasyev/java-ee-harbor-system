package ru.rsreu.harbor.controller.command.captain.product.upload;

import ru.rsreu.harbor.controller.command.captain.product.ShowProductActionsPageLogicDbImpl;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public class ShowUploadPageLogicDbImpl extends ShowProductActionsPageLogicDbImpl implements ShowUploadPageCommandLogic {
    private final ProductDao productDao;

    public ShowUploadPageLogicDbImpl(
            ProductDao productDao,
            PierAssignmentDao pierAssignmentDao,
            UserDao userDao,
            RequestStatusDao requestStatusDao) {
        super(pierAssignmentDao, userDao, requestStatusDao);
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAllInHarbour();
    }
}
