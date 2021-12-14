package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Search user by id
     * @param id identifier of user
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<User> findById(Long id);

    /**
     * Search user by login
     * @param login identifier of user
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<User> findByLogin(String login);

    /**
     * Searches for all users
     * @return List of Users and empty list if there are no users
     */
    List<User> findAll();

    /**
     * Searches for all unremoved users who are not admins and moderators
     * @return List of Users and empty list if there are no users
     */
    List<User> findAllNotDeletedWithoutAdminsAndModerators();

    /**
     * Searches for all unremoved users who are not admins and moderators without one user
     * @param user The user to exclude from the selection
     * @return List of Users and empty list if there are no users
     */
    List<User> findAllNotDeletedWithoutAdminsAndModeratorsExcludeUser(User user);

    /**
     * Save new user
     * @param user saving user
     */
    void save(User user);

    /**
     * Update new user
     * @param user updating user
     */
    void update(User user);
}
