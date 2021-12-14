package ru.rsreu.harbor.controller.command.dispatcher.approve_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"dispatcher"})
public class ApproveArrivalRequestCommand implements ActionCommand {
    private final ApproveArrivalRequestCommandLogic approveArrivalRequestCommandLogic;
    private final ArrivalRequestFormDto arrivalRequestFormDto;

    public ApproveArrivalRequestCommand(
            ApproveArrivalRequestCommandLogic approveArrivalRequestCommandLogic,
            ArrivalRequestFormDto arrivalRequestFormDto) {
        this.approveArrivalRequestCommandLogic = approveArrivalRequestCommandLogic;
        this.arrivalRequestFormDto = arrivalRequestFormDto;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        approveArrivalRequestCommandLogic.approveArrivalRequest(
                this.arrivalRequestFormDto.formModel(request));
        return new ActionCommandResult(
                Resourcer.getString("command.path.showDispatcherMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
