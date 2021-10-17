package ru.rsreu.harbor.command;

@SuppressWarnings("unused")
public enum ActionCommandsEnum {
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
    };
    
    public abstract ActionCommand getCommand(ActionCommandsFactory commandsFactory);
}
