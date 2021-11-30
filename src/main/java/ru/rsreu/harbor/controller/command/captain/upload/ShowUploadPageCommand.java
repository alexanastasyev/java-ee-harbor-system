package ru.rsreu.harbor.controller.command.captain.upload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ShowUploadPageCommand implements ActionCommand {

    private final ShowUploadPageCommandLogic showUploadPageCommandLogic;

    public ShowUploadPageCommand(ShowUploadPageCommandLogic showUploadPageCommandLogic) {
        this.showUploadPageCommandLogic = showUploadPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        request.setAttribute(
                Resourcer.getString("request.uploadCommand.parameter.products"),
                this.showUploadPageCommandLogic.getProducts()
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.uploadPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
