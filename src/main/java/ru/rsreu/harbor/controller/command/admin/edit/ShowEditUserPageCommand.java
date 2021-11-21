package ru.rsreu.harbor.controller.command.admin.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class ShowEditUserPageCommand implements ActionCommand {
    private final ShowEditUserPageCommandLogic showEditUserPageCommandLogic;

    public ShowEditUserPageCommand(ShowEditUserPageCommandLogic showEditUserPageCommandLogic) {
        this.showEditUserPageCommandLogic = showEditUserPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        String idParameter = request.getParameter(
                Resourcer.getString("request.editUser.parameter.id"));
        this.formSuccessfulJspParameters(idParameter, request);
        return new ActionCommandResult(
                Resourcer.getString("path.page.editUser"),
                ActionCommandResultTypes.FORWARD);
    }


    private void formSuccessfulJspParameters(String idParameter, HttpServletRequest request) {
        request.getSession().setAttribute(Resourcer.getString("request.editUserPage.attribute.user"),
                showEditUserPageCommandLogic.getUserById(idParameter));
        request.getSession().setAttribute(Resourcer.getString("request.editUserPage.attribute.roles"),
                showEditUserPageCommandLogic.getAllRoles());
        request.getSession().setAttribute(Resourcer.getString("request.editUserPage.attribute.statuses"),
                showEditUserPageCommandLogic.getAllStatuses());
    }
}
