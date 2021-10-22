package com.aimprosoft.services;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;

import java.util.List;

public interface IEmployeeService extends IService<Employee> {

    List<Employee> getAllByEmployeeId(Integer employeeId) throws CRUDException;
    Employee getById(Integer id) throws CRUDException;
    boolean isExistByEmail(Employee employee) throws CRUDException;
}
