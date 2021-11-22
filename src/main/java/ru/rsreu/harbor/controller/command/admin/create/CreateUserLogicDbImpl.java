package ru.rsreu.harbor.controller.command.admin.create;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

public class CreateUserLogicDbImpl implements CreateUserLogic {
    private final UserDao userDao;

    public CreateUserLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        this.userDao.save(user);
    }
}
