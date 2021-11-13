package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.RequestStatus;

public interface RequestStatusDao {
    RequestStatus findById(Long id);
}
