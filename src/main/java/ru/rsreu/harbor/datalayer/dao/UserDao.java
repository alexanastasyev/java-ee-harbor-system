package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    List<User> findAll();

    List<User> findAllNotDeletedWithoutAdminsAndModerators();

    List<User> findAllNotDeletedWithoutAdminsAndModeratorsExcludeUser(User user);

    void save(User user);

    void update(User user);
}
