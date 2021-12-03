package com.aimprosoft.services;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;

import java.util.List;

public interface IDepartmentService extends IService<Department> {

    Department createOrUpdate(Department department) throws ValidateException, CRUDException;
    Department getById(Integer id) throws CRUDException;
    Department getDepartmentByName(String departmentName) throws CRUDException;
    List<Department> getAll() throws CRUDException;
}
