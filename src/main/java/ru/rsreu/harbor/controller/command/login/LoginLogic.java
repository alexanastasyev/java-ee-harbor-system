package ru.rsreu.harbor.controller.command.login;

public interface LoginLogic {
    boolean checkLogin(String login, String password);
}
