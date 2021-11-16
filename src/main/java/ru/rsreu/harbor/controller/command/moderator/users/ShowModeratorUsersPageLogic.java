package ru.rsreu.harbor.controller.command.moderator.users;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowModeratorUsersPageLogic {
    List<User> getUsers();
}
