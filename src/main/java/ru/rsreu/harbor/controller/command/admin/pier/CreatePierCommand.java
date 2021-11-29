package ru.rsreu.harbor.controller.command.admin.pier;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class CreatePierCommand implements ActionCommand {
    private final CreatePierLogic createPierLogic;

    public CreatePierCommand(CreatePierLogic createPierLogic) {
        this.createPierLogic = createPierLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        this.createPierLogic.createPier();
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPiersPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}