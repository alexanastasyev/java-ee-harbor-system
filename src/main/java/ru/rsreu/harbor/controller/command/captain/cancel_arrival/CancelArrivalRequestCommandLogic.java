package ru.rsreu.harbor.controller.command.captain.cancel_arrival;

public interface CancelArrivalRequestCommandLogic {
    void deleteRequestByCaptain(String captainLogin);
}
