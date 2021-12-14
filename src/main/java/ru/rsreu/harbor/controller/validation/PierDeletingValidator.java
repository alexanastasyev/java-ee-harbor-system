package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.model.Pier;

public interface PierDeletingValidator {
    boolean isValidDeletion(Pier pier);
}
