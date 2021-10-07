package com.aimprosoft.services.impl;

import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.dao.impl.HibernateEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.IEmployeeService;
import com.aimprosoft.validation.OvalValidator;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Employee;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    private final IEmployeeDao employeeDao = HibernateEmployeeDao.getEmployeeDao();
    private final OvalValidator<Employee> validator = new OvalValidator<>();

    @Override
    public void createOrUpdate(Employee obj) throws ValidateException, CRUDException {
        validator.validate(obj);
        employeeDao.createOrUpdate(obj);
    }

    @Override
    public List<Employee> getAllByForeignId(Integer otherObjId) throws CRUDException {
        return employeeDao.getAllByForeignId(otherObjId);
    }

    @Override
    public Employee getById(Integer id) throws CRUDException {
        return employeeDao.getById(id);
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        employeeDao.deleteById(id);
    }

    @Override
    public boolean isExistByEmail(Employee employee) throws CRUDException {
        return employeeDao.isExistByEmail(employee);
    }
}
