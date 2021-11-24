package ru.rsreu.harbor.controller.command.admin.edit;

import ru.rsreu.harbor.controller.exception.ShowEditUserPageException;
import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowEditUserPageCommandLogic {
    User getUserById(String id) throws ShowEditUserPageException;

    List<Role> getAllRoles();

    List<Status> getAllStatuses();
}
