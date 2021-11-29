package ru.rsreu.harbor.controller.command.moderator.users;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class ShowModeratorUsersPageLogicDbImpl implements ShowModeratorUsersPageLogic {
    private final UserDao userDao;

    public ShowModeratorUsersPageLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return this.userDao.findAllNotDeletedWithoutAdminsAndModerators();
    }
}
