package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;

public interface IObjectDao<T> {

    void deleteById(Integer id) throws CRUDException;
}
