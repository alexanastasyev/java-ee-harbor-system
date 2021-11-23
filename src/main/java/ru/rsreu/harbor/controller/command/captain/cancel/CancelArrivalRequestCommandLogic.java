package ru.rsreu.harbor.controller.command.captain.cancel;

public interface CancelArrivalRequestCommandLogic {
    void deleteRequestByCaptain(String captainLogin);
}
