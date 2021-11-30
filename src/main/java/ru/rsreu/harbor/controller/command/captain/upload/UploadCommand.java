package ru.rsreu.harbor.controller.command.captain.upload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class UploadCommand implements ActionCommand {

    private final UploadCommandLogic uploadCommandLogic;

    public UploadCommand(UploadCommandLogic uploadCommandLogic) {
        this.uploadCommandLogic = uploadCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ActionCommandException {
        this.uploadCommandLogic.upload(parseProductIds(request));

        return new ActionCommandResult(
                Resourcer.getString("command.path.showCaptainMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }

    private static List<Long> parseProductIds(HttpServletRequest request) {
        String productsAsString = request.getParameter(
                Resourcer.getString("request.uploadCommand.parameter.products.ids"));
        Type token = new TypeToken<Collection<Long>>(){}.getType();
        return new Gson().fromJson(productsAsString, token);
    }
}
