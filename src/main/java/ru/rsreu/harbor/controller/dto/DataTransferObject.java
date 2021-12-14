package ru.rsreu.harbor.controller.dto;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface DataTransferObject<T> {
    T formModel(HttpServletRequest request);
}
