package ru.rsreu.harbor.controller.command.admin.user.edit;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.EditUserException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class EditUserCommand implements ActionCommand {
    private final EditUserLogic editUserLogic;
    private final DataTransferObject<User> dataTransferObject;

    public EditUserCommand(EditUserLogic editUserLogic, DataTransferObject<User> dataTransferObject) {
        this.editUserLogic = editUserLogic;
        this.dataTransferObject = dataTransferObject;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws EditUserException {
        try {
            editUserLogic.updateUser(dataTransferObject.formModel(request));
        } catch (IllegalArgumentException exception) {
            throw new EditUserException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
