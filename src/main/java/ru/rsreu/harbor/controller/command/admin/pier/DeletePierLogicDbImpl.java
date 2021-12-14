package ru.rsreu.harbor.controller.command.admin.pier;

import ru.rsreu.harbor.controller.validation.PierDeletingValidator;
import ru.rsreu.harbor.controller.validation.PierDeletingValidatorDbImpl;
import ru.rsreu.harbor.datalayer.dao.PierAssignmentDao;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.model.Pier;

public class DeletePierLogicDbImpl implements DeletePierLogic {
    private final PierDao pierDao;

    private final PierDeletingValidator pierDeletingValidator;

    public DeletePierLogicDbImpl(PierDao pierDao, PierAssignmentDao pierAssignmentDao) {
        this.pierDao = pierDao;
        this.pierDeletingValidator = new PierDeletingValidatorDbImpl(pierAssignmentDao);
    }

    @Override
    public void deletePierById(String id) {
        Pier pier = this.pierDao.findById(Long.valueOf(id)).orElseThrow(IllegalArgumentException::new);
        if (pierDeletingValidator.isValidDeletion(pier)) {
            this.pierDao.delete(this.pierDao.findById(Long.valueOf(id))
                    .orElseThrow(IllegalArgumentException::new));
        } else {
            throw new IllegalArgumentException();
        }
    }
}
