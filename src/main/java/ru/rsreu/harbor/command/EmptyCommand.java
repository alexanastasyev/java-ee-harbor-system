package ru.rsreu.harbor.command;

import com.prutzkow.resourcer.Resourcer;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return Resourcer.getString("path.page.login");
    }
}
