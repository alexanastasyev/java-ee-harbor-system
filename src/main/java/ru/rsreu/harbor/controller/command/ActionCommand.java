package ru.rsreu.harbor.controller.command;

import ru.rsreu.harbor.controller.result.ActionCommandResult;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    ActionCommandResult execute(HttpServletRequest request);
}
