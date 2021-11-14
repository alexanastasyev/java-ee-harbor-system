package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Role;

import java.util.List;

public interface RoleDao {
    Role findById(Long id);

    List<Role> findAll();
}
