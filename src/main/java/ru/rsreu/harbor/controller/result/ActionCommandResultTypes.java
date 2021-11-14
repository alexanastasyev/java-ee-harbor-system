package ru.rsreu.harbor.controller.result;

import javax.servlet.ServletException;
import java.io.IOException;

public enum ActionCommandResultTypes {
    FORWARD {
        @Override
        public void executeAction(ActionCommandResultArguments arguments) throws ServletException, IOException {
            arguments.getRequestDispatcher().forward(arguments.getRequest(), arguments.getResponse());
        }
    },
    SEND_REDIRECT {
        @Override
        public void executeAction(ActionCommandResultArguments arguments) throws IOException {
            arguments.getResponse().sendRedirect(arguments.getRequest().getContextPath() + arguments.getPage());
        }
    };

    public abstract void executeAction(ActionCommandResultArguments arguments) throws IOException, ServletException;
}
