package ru.rsreu.harbor.controller.command.report_system.page;

import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class ShowCreateReportPageCommandLogicDbImpl implements ShowCreateReportPageCommandLogic {
    private final UserDao userDao;

    public ShowCreateReportPageCommandLogicDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return this.userDao.findByLogin(userLogin).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<User> getToUsersExcludeFromUser(User fromUser) {
        return this.userDao.findAllNotDeletedWithoutAdminsAndModeratorsExcludeUser(fromUser);
    }
}
