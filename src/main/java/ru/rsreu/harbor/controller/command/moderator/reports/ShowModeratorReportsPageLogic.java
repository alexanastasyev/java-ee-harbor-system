package ru.rsreu.harbor.controller.command.moderator.reports;

import ru.rsreu.harbor.datalayer.model.Report;

import java.util.List;

public interface ShowModeratorReportsPageLogic {
    List<Report> getReports();
}
