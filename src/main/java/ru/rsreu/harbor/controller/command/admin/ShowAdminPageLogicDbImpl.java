package ru.rsreu.harbor.controller.command.admin;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class ShowAdminPageLogicDbImpl implements ShowAdminPageLogic {

    private final UserDao userDao;

    public ShowAdminPageLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
