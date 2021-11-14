package ru.rsreu.harbor.controller.command.admin.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.command.admin.page.ShowAdminPageLogic;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CreateUserCommand implements ActionCommand {
    private final CreateUserLogic createUserLogic;
    private final ShowAdminPageLogic showAdminPageLogic;
    private final CreateUserDto createUserDto;

    public CreateUserCommand(CreateUserLogic createUserLogic, ShowAdminPageLogic showAdminPageLogic, CreateUserDto createUserDto) {
        this.createUserLogic = createUserLogic;
        this.showAdminPageLogic = showAdminPageLogic;
        this.createUserDto = createUserDto;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        Map<String, Object> jspParameters = new HashMap<>();
        createUserLogic.createUser(createUserDto.formUser(request));
        jspParameters.put(Resourcer.getString("request.adminPage.attribute.users"), showAdminPageLogic.getAllUsers());
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPage"),
                ActionCommandResultTypes.SEND_REDIRECT,
                jspParameters
        );
    }
}
