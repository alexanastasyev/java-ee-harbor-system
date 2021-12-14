package ru.rsreu.harbor.controller.command.captain.unload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.exception.UnloadCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class UnloadCommand implements ActionCommand {
    private final UnloadCommandLogic unloadCommandLogic;
    private final UnloadFormDto unloadFormDto;

    public UnloadCommand(UnloadCommandLogic unloadCommandLogic, UnloadFormDto unloadFormDto) {
        this.unloadCommandLogic = unloadCommandLogic;
        this.unloadFormDto = unloadFormDto;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        try {
            unloadCommandLogic.unload(
                    request.getSession().getAttribute(Resourcer.getString("session.attribute.name.user"))
                            .toString(),
                    this.unloadFormDto.formModel(request));
        } catch (IllegalArgumentException exception) {
            throw new UnloadCommandException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showCaptainMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
