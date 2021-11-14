package ru.rsreu.harbor.controller.command.admin.create;

import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public interface CreateUserDto {
    User formUser(HttpServletRequest request);
}
