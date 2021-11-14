package ru.rsreu.harbor.controller.command;

@SuppressWarnings("unused")
public enum ActionCommands {
    LOGIN {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getLoginCommand();
        }
    },
    LOGOUT {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getLogoutCommand();
        }
    },
    SHOW_MAIN_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowMainPageCommand();
        }
    },
    SHOW_LOGIN_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowLoginPageCommand();
        }
    };
    
    public abstract ActionCommand getCommand(ActionCommandsFactory commandsFactory);
}
