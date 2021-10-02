package com.aimprosoft.services;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;

public interface IService<T> {

    void createOrUpdate(T obj) throws ValidateException, CRUDException;
    void deleteById(Integer id) throws CRUDException;
}
