package ru.rsreu.harbor.controller.command.dispatcher.main;

import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

import java.util.List;
import java.util.Map;

public interface ShowDispatcherMainPageCommandLogic {
    List<PierAssignment> getPierAssignments();

    Map<Pier, PierAssignment> getAllPiersWithAssignments();
}
