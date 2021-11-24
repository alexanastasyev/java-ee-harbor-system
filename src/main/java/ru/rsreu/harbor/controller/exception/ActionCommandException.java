package ru.rsreu.harbor.controller.exception;

import ru.rsreu.harbor.controller.result.ActionCommandResult;

import javax.servlet.http.HttpServletRequest;

public abstract class ActionCommandException extends Exception {
    public abstract ActionCommandResult getActionCommandResult(HttpServletRequest request);
}
