package ru.rsreu.harbor.controller.command.captain.unload;

import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.dao.ProductDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.Product;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class UnloadCommandLogicDbImpl implements UnloadCommandLogic {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final PierDao pierDao;

    public UnloadCommandLogicDbImpl(ProductDao productDao, UserDao userDao, PierDao pierDao) {
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
