package ru.rsreu.harbor.controller.command.admin.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ShowEditUserPageCommand implements ActionCommand {
    private final ShowEditUserPageCommandLogic showEditUserPageCommandLogic;

    public ShowEditUserPageCommand(ShowEditUserPageCommandLogic showEditUserPageCommandLogic) {
        this.showEditUserPageCommandLogic = showEditUserPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        String idParameter = request.getParameter(
                Resourcer.getString("request.editUser.parameter.id"));

        return new ActionCommandResult(
                Resourcer.getString("path.page.editUser"),
                ActionCommandResultTypes.FORWARD,
                formSuccessfulJspParameters(idParameter)
        );
    }


    private Map<String, Object> formSuccessfulJspParameters(String idParameter) {
        Map<String, Object> result = new HashMap<>();
        result.put(Resourcer.getString("request.editUserPage.attribute.user"),
                showEditUserPageCommandLogic.getUserById(Long.valueOf(idParameter)));
        result.put(Resourcer.getString("request.editUserPage.attribute.roles"),
                showEditUserPageCommandLogic.getAllRoles());
        result.put(Resourcer.getString("request.editUserPage.attribute.statuses"),
                showEditUserPageCommandLogic.getAllStatuses());
        return result;
    }
}
