package ru.rsreu.harbor.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return Resourcer.getString("path.page.index");
    }
}
