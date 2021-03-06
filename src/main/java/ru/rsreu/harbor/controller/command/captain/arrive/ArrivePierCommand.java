package ru.rsreu.harbor.controller.command.captain.arrive;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.CaptainActionException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ArrivePierCommand implements ActionCommand {
    private final ArrivePierCommandLogic arrivePierCommandLogic;

    public ArrivePierCommand(ArrivePierCommandLogic arrivePierCommandLogic) {
        this.arrivePierCommandLogic = arrivePierCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws CaptainActionException {
        try {
            this.arrivePierCommandLogic.arrivePier(
                    request.getSession().getAttribute(
                            Resourcer.getString("session.attribute.name.user")
                    ).toString()
            );
        } catch (IllegalArgumentException exception) {
            throw new CaptainActionException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showCaptainMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
