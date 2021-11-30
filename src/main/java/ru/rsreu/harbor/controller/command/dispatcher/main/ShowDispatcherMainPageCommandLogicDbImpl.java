package ru.rsreu.harbor.controller.command.dispatcher.main;

import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowDispatcherMainPageCommandLogicDbImpl implements ShowDispatcherMainPageCommandLogic {
    private final PierDao pierDao;
    private final PierAssignmentDao pierAssignmentDao;

    public ShowDispatcherMainPageCommandLogicDbImpl(PierDao pierDao, PierAssignmentDao pierAssignmentDao) {
        this.pierDao = pierDao;
        this.pierAssignmentDao = pierAssignmentDao;
    }

    private List<Pier> getPiers() {
        return this.pierDao.findAll();
    }

    @Override
    public List<PierAssignment> getPierAssignments() {
        return this.pierAssignmentDao.findAll();
    }

    @Override
    public Map<Pier, PierAssignment> getAllPiersWithAssignments() {
        List<Pier> piers = this.getPiers();
        List<PierAssignment> pierAssignments = this.getPierAssignments();
        Map<Pier, PierAssignment> result = new HashMap<>();
        piers.forEach(pier -> {
            PierAssignment pierAssignment = null;
            List<PierAssignment> currentPierAssignments =
                    pierAssignments.stream().filter(assignment -> pier.equals(assignment.getPier()))
                            .collect(Collectors.toList());
            if (!currentPierAssignments.isEmpty()) {
                pierAssignment = currentPierAssignments.get(0);
            }
            result.put(pier, pierAssignment);
        });

        return result;
    }
}
