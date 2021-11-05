package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;

import java.util.List;

public interface IDepartmentDao extends IObjectDao<Department> {

    Department getDepartmentById(Integer id) throws CRUDException;
    List<Department> getAll() throws CRUDException;
    boolean isExistByName(Department department) throws CRUDException;
}
