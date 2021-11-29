package ru.rsreu.harbor.controller.exception;

import ru.rsreu.harbor.controller.result.ActionCommandResult;

import javax.servlet.http.HttpServletRequest;

public class HandleUserBlockingException extends ActionCommandException {
    @Override
    public ActionCommandResult getActionCommandResult(HttpServletRequest request) {
        return null;
    }
}
