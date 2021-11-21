package ru.rsreu.harbor.controller.command.inactive;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class ShowInactivePageCommand implements ActionCommand {
    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        return new ActionCommandResult(
                Resourcer.getString("path.page.inactive"),
                ActionCommandResultTypes.FORWARD);
    }
}
