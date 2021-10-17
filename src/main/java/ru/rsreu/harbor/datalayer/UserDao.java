package ru.rsreu.harbor.datalayer;

import ru.rsreu.harbor.datalayer.model.User;

public interface UserDao {
    User findUserByLogin(String login);
}
