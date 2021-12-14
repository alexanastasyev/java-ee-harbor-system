package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.model.Pier;

public class PierDeletingValidatorDbImpl implements PierDeletingValidator {
    private final PierAssignmentDao pierAssignmentDao;

    public PierDeletingValidatorDbImpl(PierAssignmentDao pierAssignmentDao) {
        this.pierAssignmentDao = pierAssignmentDao;
    }

    @Override
    public boolean isValidDeletion(Pier pier) {
        return this.pierAssignmentDao.findByPier(pier).isEmpty();
    }
}
