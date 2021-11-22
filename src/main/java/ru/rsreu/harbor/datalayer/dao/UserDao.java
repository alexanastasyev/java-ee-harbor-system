package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    User findByLogin(String login);

    List<User> findAll();

    List<User> findNotDeletedWithoutAdmins();

    void save(User user);

    void update(User user);
}
