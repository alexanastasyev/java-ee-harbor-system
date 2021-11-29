package ru.rsreu.harbor.controller.command.admin.pier;

import ru.rsreu.harbor.datalayer.dao.PierDao;

public class DeletePierLogicDbImpl implements DeletePierLogic {
    private final PierDao pierDao;

    public DeletePierLogicDbImpl(PierDao pierDao) {
        this.pierDao = pierDao;
    }

    @Override
    public void deletePierById(String id) {
        this.pierDao.delete(this.pierDao.findById(Long.valueOf(id))
                .orElseThrow(IllegalArgumentException::new));
    }
}
