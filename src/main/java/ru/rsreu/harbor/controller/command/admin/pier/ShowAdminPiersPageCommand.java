package ru.rsreu.harbor.controller.command.admin.pier;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.Pier;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CommandSupportedRolesTitles(titles = "admin")
public class ShowAdminPiersPageCommand implements ActionCommand {
    private final ShowAdminPiersPageLogic showAdminPiersPageLogic;

    public ShowAdminPiersPageCommand(ShowAdminPiersPageLogic showAdminPiersPageLogic) {
        this.showAdminPiersPageLogic = showAdminPiersPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        List<Pier> piers = this.showAdminPiersPageLogic.getAllPiers();
        request.setAttribute(Resourcer.getString("request.showAdminPage.attribute.piers"), piers);
        request.setAttribute(Resourcer.getString("request.showAdminPage.attribute.availabilities"),
                this.showAdminPiersPageLogic.getPiersAvailabilities(piers));
        return new ActionCommandResult(
                Resourcer.getString("path.page.adminPiers"),
                ActionCommandResultTypes.FORWARD);
    }
}
