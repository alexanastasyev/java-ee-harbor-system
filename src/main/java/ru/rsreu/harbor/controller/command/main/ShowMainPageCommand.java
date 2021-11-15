package ru.rsreu.harbor.controller.command.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ShowMainPageCommand implements ActionCommand {
    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        return new ActionCommandResult(
                Resourcer.getString("path.page.main"),
                ActionCommandResultTypes.FORWARD,
                new HashMap<>()
        );
    }
}