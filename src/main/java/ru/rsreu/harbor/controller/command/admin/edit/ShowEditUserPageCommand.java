package ru.rsreu.harbor.controller.command.admin.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ShowEditUserPageException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class ShowEditUserPageCommand implements ActionCommand {
    private final ShowEditUserPageCommandLogic showEditUserPageCommandLogic;

    public ShowEditUserPageCommand(ShowEditUserPageCommandLogic showEditUserPageCommandLogic) {
        this.showEditUserPageCommandLogic = showEditUserPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ShowEditUserPageException {
        String idParameter = request.getParameter(
                Resourcer.getString("request.editUser.parameter.id"));
        if (!(idParameter == null || idParameter.isEmpty())) {
            this.formSuccessfulJspParameters(
                    this.showEditUserPageCommandLogic.getUserByLogin(
                            request.getSession().getAttribute(
                                    Resourcer.getString("session.attribute.name.user")
                            ).toString()
                    ),
                    this.showEditUserPageCommandLogic.getUserById(idParameter),
                    request);
        } else {
            throw new ShowEditUserPageException();
        }

        return new ActionCommandResult(
                Resourcer.getString("path.page.editUser"),
                ActionCommandResultTypes.FORWARD
        );
    }

    private void formSuccessfulJspParameters(User sessionUser, User editingUser, HttpServletRequest request) {
        request.setAttribute(Resourcer.getString("request.editUserPage.attribute.isSelfEditing"),
                this.showEditUserPageCommandLogic.isSelfEditing(sessionUser, editingUser));
        request.setAttribute(Resourcer.getString("request.editUserPage.attribute.user"),
                editingUser);
        request.setAttribute(Resourcer.getString("request.editUserPage.attribute.roles"),
                this.showEditUserPageCommandLogic.getAllRoles());
        request.setAttribute(Resourcer.getString("request.editUserPage.attribute.statuses"),
                this.showEditUserPageCommandLogic.getAllStatuses());
    }
}
