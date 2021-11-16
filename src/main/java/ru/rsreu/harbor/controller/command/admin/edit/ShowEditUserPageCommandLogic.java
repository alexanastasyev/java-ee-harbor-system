package ru.rsreu.harbor.controller.command.admin.edit;

import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowEditUserPageCommandLogic {
    User getUserById(String id);

    List<Role> getAllRoles();

    List<Status> getAllStatuses();
}
