package ru.rsreu.harbor.controller.command.captain.product.upload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.exception.ProductsActionsException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class UploadCommand implements ActionCommand {
    private final UploadCommandLogic uploadCommandLogic;

    private final UploadFormDto uploadFormDto = new UploadFormDto();

    public UploadCommand(UploadCommandLogic uploadCommandLogic) {
        this.uploadCommandLogic = uploadCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        boolean isOperationAllowed = this.uploadCommandLogic.isCaptainReadyForProductsActions(
                request.getSession().getAttribute(Resourcer.getString("session.attribute.name.user")).toString()
        );
        try {
            this.uploadCommandLogic.upload(this.uploadFormDto.formModel(request));
        } catch (IllegalArgumentException illegalArgumentException) {
            isOperationAllowed = false;
        }

        if (isOperationAllowed) {
            return new ActionCommandResult(
                    Resourcer.getString("command.path.showCaptainMainPage"),
                    ActionCommandResultTypes.SEND_REDIRECT
            );
        } else {
            throw new ProductsActionsException();
        }
    }
}
