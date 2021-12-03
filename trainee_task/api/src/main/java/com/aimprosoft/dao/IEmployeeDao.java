package com.aimprosoft.dao;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;

import java.util.List;

public interface IEmployeeDao extends IObjectDao<Employee> {

    Employee createOrUpdate(Employee employee) throws CRUDException;
    List<Employee> getAllByDepartmentId(Integer departmentId) throws CRUDException;
    Employee getById(Integer id) throws CRUDException;
    Employee getByEmail(String email) throws CRUDException;
    Employee getEmployeeByName(String employeeName) throws CRUDException;
}
