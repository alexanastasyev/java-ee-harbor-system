package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    User findByLogin(String login);

    List<User> findAll();

    void saveUser(User user);
}
