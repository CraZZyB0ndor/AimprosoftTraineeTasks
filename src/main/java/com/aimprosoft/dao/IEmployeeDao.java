package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;

import java.util.List;

public interface IEmployeeDao extends IObjectDao<Employee> {

    List<Employee> getAllByDepartmentId(Integer departmentId) throws CRUDException;
    Employee getById(Integer id) throws CRUDException;
    boolean isExistByEmail(Employee employee) throws CRUDException;
}
