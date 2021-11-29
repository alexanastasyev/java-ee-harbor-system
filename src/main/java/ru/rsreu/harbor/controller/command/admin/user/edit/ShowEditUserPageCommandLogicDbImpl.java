package ru.rsreu.harbor.controller.command.admin.user.edit;

import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public class ShowEditUserPageCommandLogicDbImpl implements ShowEditUserPageCommandLogic {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final StatusDao statusDao;

    public ShowEditUserPageCommandLogicDbImpl(UserDao userDao, RoleDao roleDao, StatusDao statusDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.statusDao = statusDao;
    }

    @Override
    public User getUserById(String id) {
        try {
            return this.userDao.findById(Long.valueOf(id)).orElseThrow(IllegalArgumentException::new);
        } catch (NumberFormatException | NullPointerException exception) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userDao.findByLogin(login).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleDao.findAll();
    }

    @Override
    public List<Status> getAllStatuses() {
        return this.statusDao.findAll();
    }

    @Override
    public boolean isSelfEditing(User sessionUser, User editingUser) {
        return sessionUser.equals(editingUser);
    }
}
