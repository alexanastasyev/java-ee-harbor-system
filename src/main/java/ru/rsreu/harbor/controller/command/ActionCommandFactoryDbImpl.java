package ru.rsreu.harbor.controller.command;

import ru.rsreu.harbor.controller.command.admin.create.*;
import ru.rsreu.harbor.controller.command.admin.edit.*;
import ru.rsreu.harbor.controller.command.admin.panel.ShowAdminPageCommand;
import ru.rsreu.harbor.controller.command.admin.panel.ShowAdminPageLogicDbImpl;
import ru.rsreu.harbor.controller.command.login.LoginCommand;
import ru.rsreu.harbor.controller.command.login.LoginLogicDbImpl;
import ru.rsreu.harbor.controller.command.login.ShowLoginPageCommand;
import ru.rsreu.harbor.controller.command.logout.LogoutCommand;
import ru.rsreu.harbor.controller.command.main.ShowMainPageCommand;
import ru.rsreu.harbor.datalayer.DaoFactory;

public class ActionCommandFactoryDbImpl implements ActionCommandsFactory {
    private final DaoFactory daoFactory;

    public ActionCommandFactoryDbImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ActionCommand getLoginCommand() {
        return new LoginCommand(new LoginLogicDbImpl(daoFactory.getUserDao()));
    }

    @Override
    public ActionCommand getLogoutCommand() {
        return new LogoutCommand();
    }

    @Override
    public ActionCommand getShowMainPageCommand() {
        return new ShowMainPageCommand();
    }

    @Override
    public ActionCommand getShowLoginPageCommand() {
        return new ShowLoginPageCommand();
    }

    @Override
    public ActionCommand getShowAdminPageCommand() {
        return new ShowAdminPageCommand(new ShowAdminPageLogicDbImpl(daoFactory.getUserDao()));
    }

    @Override
    public ActionCommand getShowCreateUserPageCommand() {
        return new ShowCreateUserPageCommand(new ShowCreateUserPageLogicDbImpl(daoFactory.getRoleDao()));
    }

    @Override
    public ActionCommand getCreateUserCommand() {
        return new CreateUserCommand(
                new CreateUserLogicDbImpl(daoFactory.getUserDao()),
                new CreateUserDataTransferObjectDbImpl(daoFactory.getRoleDao(), daoFactory.getStatusDao()));
    }

    @Override
    public ActionCommand getShowEditUserPageCommand() {
        return new ShowEditUserPageCommand(
                new ShowEditUserPageCommandLogicDbImpl(
                        this.daoFactory.getUserDao(), this.daoFactory.getRoleDao(), this.daoFactory.getStatusDao()));
    }

    @Override
    public ActionCommand getEditUserCommand() {
        return new EditUserCommand(
                new EditUserLogicDbImpl(this.daoFactory.getUserDao()),
                new EditUserDataTransferObjectDbImpl(
                        this.daoFactory.getRoleDao(),
                        this.daoFactory.getStatusDao()));
    }
}
