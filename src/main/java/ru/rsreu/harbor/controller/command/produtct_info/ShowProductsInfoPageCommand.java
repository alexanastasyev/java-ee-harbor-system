package ru.rsreu.harbor.controller.command.produtct_info;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"dispatcher"})
public class ShowProductsInfoPageCommand implements ActionCommand {
    private final ShowProductsInfoLogic showProductsInfoLogic;

    public ShowProductsInfoPageCommand(ShowProductsInfoLogic showProductsInfoLogic) {
        this.showProductsInfoLogic = showProductsInfoLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        request.setAttribute(
                Resourcer.getString("request.showProductsInfoCommand.parameter.products"),
                this.showProductsInfoLogic.getAllProducts()
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.showProductInfoPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}