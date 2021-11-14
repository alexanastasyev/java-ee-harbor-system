package ru.rsreu.harbor.controller.command;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class EmptyCommand implements ActionCommand {

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        return new ActionCommandResult(
                Resourcer.getString("path.page.login"),
                ActionCommandResultTypes.SEND_REDIRECT,
                new HashMap<>());
    }
}
