package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    Optional<Role> findById(Long id);

    List<Role> findAll();
}
