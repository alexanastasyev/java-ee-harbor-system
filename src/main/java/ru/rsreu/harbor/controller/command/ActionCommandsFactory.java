package ru.rsreu.harbor.controller.command;

public interface ActionCommandsFactory {
    ActionCommand getLoginCommand();

    ActionCommand getLogoutCommand();

    ActionCommand getShowMainPageCommand();

    ActionCommand getShowLoginPageCommand();

    ActionCommand getShowAdminPageCommand();

    ActionCommand getShowCreateUserPageCommand();

    ActionCommand getCreateUserCommand();

    ActionCommand getShowEditUserPageCommand();

    ActionCommand getEditUserCommand();

    ActionCommand getShowModeratorUsersPageCommand();

    ActionCommand getHandleUserBlockingCommand();

    ActionCommand getShowModeratorReportsPageCommand();

    ActionCommand getShowReportPageCommand();

    ActionCommand getDeleteReportCommand();

    ActionCommand getShowInactivePageCommand();
}
