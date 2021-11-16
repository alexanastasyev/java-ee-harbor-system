package ru.rsreu.harbor.controller.command.moderator.users;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class HandleUserBlockingCommand implements ActionCommand {
    private final HandleUserBlockingLogic handleUserBlockingLogic;

    public HandleUserBlockingCommand(HandleUserBlockingLogic handleUserBlockingLogic) {
        this.handleUserBlockingLogic = handleUserBlockingLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        handleUserBlockingLogic.handleUserBlocking(
                request.getParameter(Resourcer.getString("request.handleUserBlockingCommand.parameter.id"))
        );
        String page = request.getParameter(
                Resourcer.getString("request.handleUserBlockingCommand.parameter.nextPage")
        );
        return new ActionCommandResult(
                page,
                ActionCommandResultTypes.SEND_REDIRECT,
                new HashMap<>()
        );
    }
}
