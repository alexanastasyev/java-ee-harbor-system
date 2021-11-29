package ru.rsreu.harbor.controller.command.admin.user.create;

import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;

public class ShowCreateUserPageLogicDbImpl implements ShowCreateUserPageLogic {
    private final RoleDao roleDao;

    public ShowCreateUserPageLogicDbImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleDao.findAll();
    }
}
