package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.datalayer.model.RequestStatus;
import ru.rsreu.harbor.datalayer.model.User;

public interface CaptainActionValidator {
    boolean isCaptainActionValid(User captain, RequestStatus desiredStatus);
}
