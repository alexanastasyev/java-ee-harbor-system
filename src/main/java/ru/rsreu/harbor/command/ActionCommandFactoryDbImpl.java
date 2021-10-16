package ru.rsreu.harbor.command;

import ru.rsreu.harbor.command.login.LoginCommand;
import ru.rsreu.harbor.command.login.LoginLogicDbImpl;
import ru.rsreu.harbor.command.login.LogoutCommand;
import ru.rsreu.harbor.datalayer.DaoFactory;

public class ActionCommandFactoryDbImpl implements ActionCommandsFactory {
    private DaoFactory daoFactory;

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
}
