package ru.rsreu.harbor.controller.command.admin.pier;

import ru.rsreu.harbor.datalayer.dao.PierDao;

public class CreatePierLogicDbImpl implements CreatePierLogic {
    private final PierDao pierDao;

    public CreatePierLogicDbImpl(PierDao pierDao) {
        this.pierDao = pierDao;
    }

    @Override
    public void createPier() {
        this.pierDao.save();
    }
}
