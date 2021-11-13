package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.User;

public interface UserDao {
    User findById(Long id);

    User findByLogin(String login);
}
