package ru.rsreu.harbor.controller.command.moderator.users;

import ru.rsreu.harbor.controller.exception.HandleUserBlockingException;

public interface HandleUserBlockingLogic {
    void handleUserBlocking(String id) throws HandleUserBlockingException;
}
