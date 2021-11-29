package ru.rsreu.harbor.controller.dto;

import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.exception.CreateUserException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface DataTransferObject<T> {
    T formModel(HttpServletRequest request);
}
