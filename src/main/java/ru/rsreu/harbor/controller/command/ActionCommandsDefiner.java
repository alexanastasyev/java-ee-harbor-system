package ru.rsreu.harbor.controller.command;

import com.prutzkow.resourcer.Resourcer;

import javax.servlet.ServletRequest;

public class ActionCommandsDefiner {
    public static ActionCommand defineCommand(ServletRequest request, ActionCommandsFactory commandsFactory) {
        ActionCommand actionCommand = new EmptyCommand();
        String action = request.getParameter(Resourcer.getString("request.parameter.command"));
        if (action == null || action.isEmpty()) {
            return actionCommand;
        }
        try {
            ActionCommands currentEnum = ActionCommands.valueOf(action.toUpperCase());
            actionCommand = currentEnum.getCommand(commandsFactory);
        } catch (IllegalArgumentException e) {
            request.setAttribute(Resourcer.getString("request.attribute.wrongAction"),
                    action + Resourcer.getString("message.wrongAction"));
        }
        return actionCommand;
    }
}
