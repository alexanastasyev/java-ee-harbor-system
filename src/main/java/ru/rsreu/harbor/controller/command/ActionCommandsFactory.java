package ru.rsreu.harbor.controller.command;

public interface ActionCommandsFactory {
    ActionCommand getLoginCommand();

    ActionCommand getLogoutCommand();

    ActionCommand getShowMainPageCommand();

    ActionCommand getShowLoginPageCommand();

    ActionCommand getShowAdminPageCommand();
}
