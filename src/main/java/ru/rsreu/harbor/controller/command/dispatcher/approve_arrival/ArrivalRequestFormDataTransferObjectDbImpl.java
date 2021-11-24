package ru.rsreu.harbor.controller.command.dispatcher.approve_arrival;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;

import javax.servlet.http.HttpServletRequest;

public class ArrivalRequestFormDataTransferObjectDbImpl implements DataTransferObject<ArrivalRequestForm> {

    @Override
    public ArrivalRequestForm formModel(HttpServletRequest request) {
        return new ArrivalRequestForm(
                Long.valueOf(request.getParameter(Resourcer.getString(
                        "request.approveArrivalRequestCommand.parameter.pierAssignmentId"
                ))),
                Long.valueOf(request.getParameter(Resourcer.getString(
                        "request.approveArrivalRequestCommand.parameter.chosenPier"
                )))
        );
    }
}
