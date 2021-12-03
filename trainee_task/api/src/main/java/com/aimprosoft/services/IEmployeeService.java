package com.aimprosoft.services;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Employee;

import java.util.List;

public interface IEmployeeService extends IService<Employee> {

    Employee createOrUpdate(Employee employee) throws ValidateException, CRUDException;
    List<Employee> getAllByEmployeeId(Integer employeeId) throws CRUDException;
    Employee getEmployeeByName(String employeeName) throws CRUDException;
    Employee getById(Integer id) throws CRUDException;
    Employee getByEmail(String email) throws CRUDException;
}
