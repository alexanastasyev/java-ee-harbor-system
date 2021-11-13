package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Role;

public interface RoleDao {
    Role findById(Long id);
}
