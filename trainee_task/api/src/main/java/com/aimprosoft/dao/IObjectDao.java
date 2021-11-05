package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;

public interface IObjectDao<T> {

    void createOrUpdate(T obj) throws CRUDException;
    void deleteById(Integer id) throws CRUDException;
}
