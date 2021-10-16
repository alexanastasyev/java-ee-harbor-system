package ru.rsreu.harbor.command;

public interface ActionCommandsFactory {
    ActionCommand getLoginCommand();

    ActionCommand getLogoutCommand();
}
