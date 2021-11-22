package ru.rsreu.harbor.controller.command.admin.edit;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

public class EditUserLogicDbImpl implements EditUserLogic{
    private final UserDao userDao;

    public EditUserLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updateUser(User user) {
        this.userDao.update(user);
    }
}
