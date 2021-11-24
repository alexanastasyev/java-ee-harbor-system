package ru.rsreu.harbor.controller.command.report_system.page;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface ShowCreateReportPageCommandLogic {
    User getUserByLogin(String userLogin);

    List<User> getToUsersExcludeFromUser(User fromUser);
}
