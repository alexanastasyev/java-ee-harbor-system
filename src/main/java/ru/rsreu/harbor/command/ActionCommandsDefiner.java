package ru.rsreu.harbor.command;

import com.prutzkow.resourcer.Resourcer;

import javax.servlet.http.HttpServletRequest;

public class ActionCommandsDefiner {
    public static ActionCommand defineCommand(HttpServletRequest request, ActionCommandsFactory commandsFactory) {
        ActionCommand actionCommand = new EmptyCommand();
        String action = request.getParameter(Resourcer.getString("request.parameter.command")).toUpperCase();
        //noinspection ConstantConditions
        if (action == null || action.isEmpty()) {
            return actionCommand;
        }
        try {
            ActionCommandsEnum currentEnum = ActionCommandsEnum.valueOf(action);
            actionCommand = currentEnum.getCommand(commandsFactory);
        } catch (IllegalArgumentException e) {
            request.setAttribute(Resourcer.getString("request.attribute.wrongAction"),
                    action + Resourcer.getString("message.wrongAction"));
        }
        return actionCommand;
    }
}
