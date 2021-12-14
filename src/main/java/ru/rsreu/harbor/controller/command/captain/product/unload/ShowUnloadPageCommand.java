package ru.rsreu.harbor.controller.command.captain.product.unload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.command.captain.product.ShowProductActionsPageLogic;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.exception.CaptainActionException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ShowUnloadPageCommand implements ActionCommand {
    private final ShowProductActionsPageLogic showUnloadUploadPageLogic;

    public ShowUnloadPageCommand(ShowProductActionsPageLogic showUnloadUploadPageLogic) {
        this.showUnloadUploadPageLogic = showUnloadUploadPageLogic;
    }
    
    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        if (this.showUnloadUploadPageLogic.isCaptainReadyForShowProductsActionsPage(
                request.getSession().getAttribute(
                        Resourcer.getString("session.attribute.name.user")).toString())) {
            return new ActionCommandResult(
                    Resourcer.getString("path.page.unloadPage"),
                    ActionCommandResultTypes.FORWARD
            );
        } else {
            throw new CaptainActionException();
        }
    }
}
