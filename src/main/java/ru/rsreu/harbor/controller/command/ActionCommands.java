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
    },
    SHOW_MODERATOR_USERS_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowModeratorUsersPageCommand();
        }
    },
    HANDLE_USER_BLOCKING {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getHandleUserBlockingCommand();
        }
    },
    SHOW_MODERATOR_REPORTS_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowModeratorReportsPageCommand();
        }
    },
    SHOW_REPORT_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowReportPageCommand();
        }
    },
    DELETE_REPORT {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getDeleteReportCommand();
        }
    },
    SHOW_INACTIVE_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowInactivePageCommand();
        }
    },
    SHOW_ADMIN_PIERS_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowAdminPierPageCommand();
        }
    },
    CREATE_PIER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getCreatePierCommand();
        }
    },
    DELETE_PIER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getDeletePierCommand();
        }
    },
    SHOW_CAPTAIN_MAIN_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowCaptainMainPageCommand();
        }
    },
    REQUEST_ARRIVAL {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getRequestArrivalCommand();
        }
    },
    CANCEL_ARRIVAL_REQUEST{
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getCancelArrivalRequestCommand();
        }
    },
    ARRIVE_PIER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getArrivePierCommand();
        }
    },
    REQUEST_DEPARTMENT {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getRequestDepartmentCommand();
        }
    },
    CANCEL_DEPARTMENT_REQUEST {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getArrivePierCommand();
        }
    },
    DEPART_PIER {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getCancelArrivalRequestCommand();
        }
    },
    SHOW_DISPATCHER_MAIN_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowDispatcherMainPageCommand();
        }
    },
    APPROVE_ARRIVAL_REQUEST {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getApproveArrivalRequestCommand();
        }
    },
    APPROVE_DEPARTMENT_REQUEST {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getApproveDepartmentRequestCommand();
        }
    },
    SHOW_CREATE_REPORT_PAGE {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getShowCreateReportPageCommand();
        }
    },
    CREATE_REPORT {
        @Override
        public ActionCommand getCommand(ActionCommandsFactory commandsFactory) {
            return commandsFactory.getCreateReportCommand();
        }
    };

    public abstract ActionCommand getCommand(ActionCommandsFactory commandsFactory);
}
