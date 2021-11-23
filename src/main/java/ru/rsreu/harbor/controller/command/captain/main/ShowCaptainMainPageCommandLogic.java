package ru.rsreu.harbor.controller.command.captain.main;

import ru.rsreu.harbor.datalayer.model.PierAssignment;

public interface ShowCaptainMainPageCommandLogic {
    PierAssignment getPierAssignmentByCaptain(String captainLogin);
}
