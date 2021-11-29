package ru.rsreu.harbor.controller.command.admin.user.edit;

import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowEditUserPageCommandLogic {
    User getUserById(String id);

    User getUserByLogin(String login);

    List<Role> getAllRoles();

    List<Status> getAllStatuses();

    boolean isSelfEditing(User sessionUser, User editingUser);
}
