package ru.rsreu.harbor.datalayer;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface UserDao {
    User findUserByLogin(String login);
}
