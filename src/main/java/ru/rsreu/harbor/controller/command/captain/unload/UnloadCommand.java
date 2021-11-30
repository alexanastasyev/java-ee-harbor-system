package ru.rsreu.harbor.controller.command.captain.unload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

@CommandSupportedRolesTitles(titles = {"captain"})
public class UnloadCommand implements ActionCommand {
    private final UnloadCommandLogic unloadCommandLogic;

    public UnloadCommand(UnloadCommandLogic unloadCommandLogic) {
        this.unloadCommandLogic = unloadCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        unloadCommandLogic.unload(
                request.getSession().getAttribute(Resourcer.getString("session.attribute.name.user"))
                        .toString(),
                    parseProductForms(request)
                );
        return new ActionCommandResult(
                Resourcer.getString("command.path.showCaptainMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }

    private static List<ProductForm> parseProductForms(HttpServletRequest request) {
        String productsAsString = request.getParameter(
                Resourcer.getString("request.unloadCommand.parameter.products"));
        Type token = new TypeToken<Collection<ProductForm>>(){}.getType();
        return new Gson().fromJson(productsAsString, token);
    }
}
