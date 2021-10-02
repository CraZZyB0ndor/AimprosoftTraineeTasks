package com.aimprosoft.services;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;

import java.util.List;

public interface IDepartmentService extends IService<Department> {

    List<Department> getAll() throws CRUDException;
    boolean isExistByName(Department department) throws CRUDException;
}
