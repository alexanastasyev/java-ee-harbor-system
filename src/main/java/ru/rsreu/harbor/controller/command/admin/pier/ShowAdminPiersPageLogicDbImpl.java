package ru.rsreu.harbor.controller.command.admin.pier;

import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.model.Pier;

import java.util.List;
import java.util.stream.Collectors;

public class ShowAdminPiersPageLogicDbImpl implements ShowAdminPiersPageLogic {
    private final PierDao pierDao;
    private final PierAssignmentDao pierAssignmentDao;

    public ShowAdminPiersPageLogicDbImpl(PierDao pierDao, PierAssignmentDao pierAssignmentDao) {
        this.pierDao = pierDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public List<Pier> getAllPiers() {
        return this.pierDao.findAll();
    }

    @Override
    public List<Boolean> getPiersAvailabilities(List<Pier> piers) {
        return piers
                .stream()
                .map(pier -> this.pierAssignmentDao.findByPier(pier).isEmpty())
                .collect(Collectors.toList());
    }
}
