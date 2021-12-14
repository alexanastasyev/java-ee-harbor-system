package ru.rsreu.harbor.controller.command.captain.product.unload;

import ru.rsreu.harbor.controller.command.captain.product.ProductsActionsLogicDbImpl;
import ru.rsreu.harbor.datalayer.dao.*;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.Product;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class UnloadCommandLogicDbImpl extends ProductsActionsLogicDbImpl implements UnloadCommandLogic {
    private final ProductDao productDao;
    private final UserDao userDao;
    private final PierDao pierDao;

    public UnloadCommandLogicDbImpl(
            ProductDao productDao,
            UserDao userDao,
            PierDao pierDao,
            PierAssignmentDao pierAssignmentDao,
            RequestStatusDao requestStatusDao) {
        super(userDao, pierAssignmentDao, requestStatusDao);
        this.productDao = productDao;
        this.userDao = userDao;
        this.pierDao = pierDao;
    }

    @Override
    public void unload(String login, List<ProductForm> productForms) {
        User user = this.userDao.findByLogin(login).orElseThrow(IllegalArgumentException::new);
        Pier pier = this.pierDao.findByUser(user).orElseThrow(IllegalArgumentException::new);

        productForms.forEach(productForm -> productDao.save(
            new Product(
                null,
                productForm.getTitle(),
                productForm.getQuantity(),
                user,
                pier,
                null,
                null
            )
        ));
    }
}
