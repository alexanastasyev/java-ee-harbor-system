package ru.rsreu.harbor.controller.command.captain.product.unload;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.exception.ProductsActionsException;
import ru.rsreu.harbor.controller.exception.UnloadCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CommandSupportedRolesTitles(titles = {"captain"})
public class UnloadCommand implements ActionCommand {
    private final UnloadCommandLogic unloadCommandLogic;
    private final UnloadFormDto unloadFormDto;

    public UnloadCommand(UnloadCommandLogic unloadCommandLogic, UnloadFormDto unloadFormDto) {
        this.unloadCommandLogic = unloadCommandLogic;
        this.unloadFormDto = unloadFormDto;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        String captainLogin =  request
                .getSession()
                .getAttribute(Resourcer.getString("session.attribute.name.user"))
                .toString();
        boolean isOperationAllowed = this.unloadCommandLogic.isCaptainReadyForProductsActions(captainLogin);
        List<ProductForm> productForms;
        try {
            productForms = this.unloadFormDto.formModel(request);
        } catch (IllegalArgumentException exception) {
            throw new UnloadCommandException();
        }
        if (isOperationAllowed) {
            this.unloadCommandLogic.unload(captainLogin, productForms);
            return new ActionCommandResult(
                    Resourcer.getString("command.path.showCaptainMainPage"),
                    ActionCommandResultTypes.SEND_REDIRECT
            );
        } else {
            throw new ProductsActionsException();
        }
    }
}
