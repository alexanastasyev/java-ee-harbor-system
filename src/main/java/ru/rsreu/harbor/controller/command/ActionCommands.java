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
    },
    SHOW_ADMIN_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowAdminPageCommand();
        }
    },
    SHOW_CREATE_USER_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowCreateUserPageCommand();
        }
    },
    CREATE_USER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getCreateUserCommand();
        }
    },
    SHOW_EDIT_USER_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowEditUserPageCommand();
        }
    },
    EDIT_USER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getEditUserCommand();
        }
    };
    
    public abstract ActionCommand getCommand(ActionCommandsFactory commandsFactory);
}
