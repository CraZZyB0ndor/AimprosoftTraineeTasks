package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;

import java.util.List;

public interface IDepartmentDao extends IObjectDao<Department> {

    List<Department> getAll() throws CRUDException;
    boolean isExistByName(Department department) throws CRUDException;
}
