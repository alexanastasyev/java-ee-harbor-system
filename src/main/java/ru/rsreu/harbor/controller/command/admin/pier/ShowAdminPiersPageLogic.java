package ru.rsreu.harbor.controller.command.admin.pier;

import ru.rsreu.harbor.datalayer.model.Pier;

import java.util.List;

public interface ShowAdminPiersPageLogic {
    List<Pier> getAllPiers();

    List<Boolean> getPiersAvailabilities(List<Pier> piers);
}
