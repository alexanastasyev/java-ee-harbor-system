package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    /**
     * Search role by id
     * @param id identifier of role
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Role> findById(Long id);

    /**
     * Searches for all roles
     * @return List of Roles and empty list if there are no roles
     */
    List<Role> findAll();
}
