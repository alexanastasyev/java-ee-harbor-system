package ru.rsreu.harbor.controller.dto;

import javax.servlet.http.HttpServletRequest;

public interface DataTransferObject<T> {
    T formModel(HttpServletRequest request);
}
