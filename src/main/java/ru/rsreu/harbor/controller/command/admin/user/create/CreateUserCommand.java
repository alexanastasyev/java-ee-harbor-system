package ru.rsreu.harbor.controller.command.admin.user.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.CreateUserException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class CreateUserCommand implements ActionCommand {
    private final CreateUserLogic createUserLogic;
    private final DataTransferObject<User> dataTransferObject;

    public CreateUserCommand(CreateUserLogic createUserLogic, DataTransferObject<User> dataTransferObject) {
        this.createUserLogic = createUserLogic;
        this.dataTransferObject = dataTransferObject;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws CreateUserException {
        try {
            createUserLogic.createUser(dataTransferObject.formModel(request));
        } catch (IllegalArgumentException exception) {
            throw new CreateUserException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
