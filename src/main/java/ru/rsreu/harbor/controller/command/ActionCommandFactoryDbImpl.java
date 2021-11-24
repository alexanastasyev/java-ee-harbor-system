package ru.rsreu.harbor.controller.command;

import ru.rsreu.harbor.controller.command.admin.create.*;
import ru.rsreu.harbor.controller.command.admin.edit.*;
import ru.rsreu.harbor.controller.command.admin.panel.ShowAdminPageCommand;
import ru.rsreu.harbor.controller.command.admin.panel.ShowAdminPageLogicDbImpl;
import ru.rsreu.harbor.controller.command.admin.pier.*;
import ru.rsreu.harbor.controller.command.captain.arrive.ArrivePierCommand;
import ru.rsreu.harbor.controller.command.captain.arrive.ArrivePierCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.captain.request_arrival.RequestArrivalCommand;
import ru.rsreu.harbor.controller.command.captain.request_arrival.RequestArrivalCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.captain.cancel_arrival.CancelArrivalRequestCommand;
import ru.rsreu.harbor.controller.command.captain.cancel_arrival.CancelArrivalRequestCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.captain.main.ShowCaptainMainPageCommand;
import ru.rsreu.harbor.controller.command.captain.main.ShowCaptainMainPageCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.captain.request_department.RequestDepartmentCommand;
import ru.rsreu.harbor.controller.command.captain.request_department.RequestDepartmentCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.dispatcher.approve_arrival.ApproveArrivalRequestCommand;
import ru.rsreu.harbor.controller.command.dispatcher.approve_arrival.ApproveArrivalRequestCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.dispatcher.approve_arrival.ArrivalRequestFormDataTransferObjectDbImpl;
import ru.rsreu.harbor.controller.command.dispatcher.approve_department.ApproveDepartmentRequestCommand;
import ru.rsreu.harbor.controller.command.dispatcher.approve_department.ApproveDepartmentRequestCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.dispatcher.main.ShowDispatcherMainPageCommand;
import ru.rsreu.harbor.controller.command.dispatcher.main.ShowDispatcherMainPageCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.inactive.ShowInactivePageCommand;
import ru.rsreu.harbor.controller.command.login.LoginCommand;
import ru.rsreu.harbor.controller.command.login.LoginCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.login.ShowLoginPageCommand;
import ru.rsreu.harbor.controller.command.logout.LogoutCommand;
import ru.rsreu.harbor.controller.command.main.ShowMainPageCommand;
import ru.rsreu.harbor.controller.command.moderator.reports.*;
import ru.rsreu.harbor.controller.command.moderator.users.HandleUserBlockingCommand;
import ru.rsreu.harbor.controller.command.moderator.users.HandleUserBlockingLogicDbImpl;
import ru.rsreu.harbor.controller.command.moderator.users.ShowModeratorUsersPageCommand;
import ru.rsreu.harbor.controller.command.moderator.users.ShowModeratorUsersPageLogicDbImpl;
import ru.rsreu.harbor.controller.command.report_system.create.CreateReportCommand;
import ru.rsreu.harbor.controller.command.report_system.create.CreateReportCommandLogicDbImpl;
import ru.rsreu.harbor.controller.command.report_system.create.CreateReportDataTransferObjectDbImpl;
import ru.rsreu.harbor.controller.command.report_system.page.ShowCreateReportPageCommand;
import ru.rsreu.harbor.controller.command.report_system.page.ShowCreateReportPageCommandLogicDbImpl;
import ru.rsreu.harbor.datalayer.DaoFactory;

public class ActionCommandFactoryDbImpl implements ActionCommandsFactory {
    private final DaoFactory daoFactory;

    public ActionCommandFactoryDbImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ActionCommand getLoginCommand() {
        return new LoginCommand(new LoginCommandLogicDbImpl(daoFactory.getUserDao()));
    }

    @Override
    public ActionCommand getLogoutCommand() {
        return new LogoutCommand();
    }

    @Override
    public ActionCommand getShowMainPageCommand() {
        return new ShowMainPageCommand();
    }

    @Override
    public ActionCommand getShowLoginPageCommand() {
        return new ShowLoginPageCommand();
    }

    @Override
    public ActionCommand getShowAdminPageCommand() {
        return new ShowAdminPageCommand(new ShowAdminPageLogicDbImpl(daoFactory.getUserDao()));
    }

    @Override
    public ActionCommand getShowCreateUserPageCommand() {
        return new ShowCreateUserPageCommand(new ShowCreateUserPageLogicDbImpl(daoFactory.getRoleDao()));
    }

    @Override
    public ActionCommand getCreateUserCommand() {
        return new CreateUserCommand(
                new CreateUserLogicDbImpl(daoFactory.getUserDao()),
                new CreateUserDataTransferObjectDbImpl(daoFactory.getRoleDao(), daoFactory.getStatusDao()));
    }

    @Override
    public ActionCommand getShowEditUserPageCommand() {
        return new ShowEditUserPageCommand(
                new ShowEditUserPageCommandLogicDbImpl(
                        this.daoFactory.getUserDao(), this.daoFactory.getRoleDao(), this.daoFactory.getStatusDao()));
    }

    @Override
    public ActionCommand getEditUserCommand() {
        return new EditUserCommand(
                new EditUserLogicDbImpl(this.daoFactory.getUserDao()),
                new EditUserDataTransferObjectDbImpl(
                        this.daoFactory.getRoleDao(),
                        this.daoFactory.getStatusDao()));
    }

    @Override
    public ActionCommand getShowModeratorUsersPageCommand() {
        return new ShowModeratorUsersPageCommand(new ShowModeratorUsersPageLogicDbImpl(this.daoFactory.getUserDao()));
    }

    @Override
    public ActionCommand getHandleUserBlockingCommand() {
        return new HandleUserBlockingCommand(
                new HandleUserBlockingLogicDbImpl(this.daoFactory.getUserDao(), this.daoFactory.getStatusDao())
        );
    }

    @Override
    public ActionCommand getShowModeratorReportsPageCommand() {
        return new ShowModeratorReportsPageCommand(
                new ShowModeratorReportsPageLogicDbImpl(this.daoFactory.getReportDao())
        );
    }

    @Override
    public ActionCommand getShowReportPageCommand() {
        return new ShowReportPageCommand(new ShowReportPageLogicDbImpl(this.daoFactory.getReportDao()));
    }

    @Override
    public ActionCommand getDeleteReportCommand() {
        return new DeleteReportCommand(new DeleteReportLogicDbImpl(this.daoFactory.getReportDao()));
    }

    @Override
    public ActionCommand getShowInactivePageCommand() {
        return new ShowInactivePageCommand();
    }

    @Override
    public ActionCommand getShowAdminPierPageCommand() {
        return new ShowAdminPiersPageCommand(
                new ShowAdminPiersPageLogicDbImpl(this.daoFactory.getPierDao(),
                        this.daoFactory.getPierAssignmentDao()));
    }

    @Override
    public ActionCommand getCreatePierCommand() {
        return new CreatePierCommand(new CreatePierLogicDbImpl(this.daoFactory.getPierDao()));
    }

    @Override
    public ActionCommand getDeletePierCommand() {
        return new DeletePierCommand(new DeletePierLogicDbImpl(this.daoFactory.getPierDao()));
    }

    @Override
    public ActionCommand getShowCaptainMainPageCommand() {
        return new ShowCaptainMainPageCommand(new ShowCaptainMainPageCommandLogicDbImpl(
                this.daoFactory.getUserDao(),
                this.daoFactory.getPierDao(), this.daoFactory.getRequestStatusDao(), this.daoFactory.getPierAssignmentDao()
        ));
    }

    @Override
    public ActionCommand getRequestArrivalCommand() {
        return new RequestArrivalCommand(new RequestArrivalCommandLogicDbImpl(
                this.daoFactory.getUserDao(),
                this.daoFactory.getPierAssignmentDao(),
                this.daoFactory.getRequestStatusDao()
        ));
    }

    @Override
    public ActionCommand getCancelArrivalRequestCommand() {
        return new CancelArrivalRequestCommand(new CancelArrivalRequestCommandLogicDbImpl(
                this.daoFactory.getUserDao(),
                this.daoFactory.getPierAssignmentDao()
        ));
    }

    @Override
    public ActionCommand getArrivePierCommand() {
        return new ArrivePierCommand(new ArrivePierCommandLogicDbImpl(
           this.daoFactory.getUserDao(),
           this.daoFactory.getRequestStatusDao(),
           this.daoFactory.getPierAssignmentDao()
        ));
    }

    @Override
    public ActionCommand getRequestDepartmentCommand() {
        return new RequestDepartmentCommand(new RequestDepartmentCommandLogicDbImpl(
                this.daoFactory.getUserDao(),
                this.daoFactory.getRequestStatusDao(),
                this.daoFactory.getPierAssignmentDao()
        ));
    }

    @Override
    public ActionCommand getShowDispatcherMainPageCommand() {
        return new ShowDispatcherMainPageCommand(new ShowDispatcherMainPageCommandLogicDbImpl(
                this.daoFactory.getPierDao(),
                this.daoFactory.getPierAssignmentDao()
        ));
    }

    @Override
    public ActionCommand getApproveArrivalRequestCommand() {
        return new ApproveArrivalRequestCommand(
                new ApproveArrivalRequestCommandLogicDbImpl(
                        this.daoFactory.getPierDao(),
                        this.daoFactory.getRequestStatusDao(),
                        this.daoFactory.getPierAssignmentDao()),
                new ArrivalRequestFormDataTransferObjectDbImpl()
        );
    }

    @Override
    public ActionCommand getApproveDepartmentRequestCommand() {
        return new ApproveDepartmentRequestCommand(
                new ApproveDepartmentRequestCommandLogicDbImpl(
                        this.daoFactory.getRequestStatusDao(),
                        this.daoFactory.getPierAssignmentDao()
                )
        );
    }

    @Override
    public ActionCommand getShowCreateReportPageCommand() {
        return new ShowCreateReportPageCommand(
                new ShowCreateReportPageCommandLogicDbImpl(
                        this.daoFactory.getUserDao()
                )
        );
    }

    @Override
    public ActionCommand getCreateReportCommand() {
        return new CreateReportCommand(
                new CreateReportCommandLogicDbImpl(
                        this.daoFactory.getReportDao()
                ),
                new CreateReportDataTransferObjectDbImpl(
                        this.daoFactory.getUserDao()
                )
        );
    }
}
