package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;

import java.util.List;

public interface IDepartmentDao extends IObjectDao<Department> {


    Department createOrUpdate(Department department) throws CRUDException;
    Department getById(Integer id) throws CRUDException;
    Department getDepartmentByName(String departmentName) throws CRUDException;
    List<Department> getAll() throws CRUDException;
}
