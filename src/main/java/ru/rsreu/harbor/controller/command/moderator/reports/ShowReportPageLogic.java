package ru.rsreu.harbor.controller.command.moderator.reports;

import ru.rsreu.harbor.datalayer.model.Report;

public interface ShowReportPageLogic {
    Report getReportById(String id);
}
