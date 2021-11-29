package ru.rsreu.harbor.controller.validation;

public interface HandleUserBlockingOrCreateReportValidator {
    boolean isValidBlockingOrReportCreating(String idParameter);
}
