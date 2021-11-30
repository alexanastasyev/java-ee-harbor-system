package ru.rsreu.harbor.controller.command.captain.unload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ShowUnloadPageCommand implements ActionCommand {
    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        return new ActionCommandResult(
                Resourcer.getString("path.page.unloadPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
