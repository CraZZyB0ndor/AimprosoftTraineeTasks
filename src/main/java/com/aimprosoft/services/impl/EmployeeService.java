package com.aimprosoft.services.impl;

import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.IEmployeeService;
import com.aimprosoft.validation.OvalValidator;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService implements IEmployeeService {

    private final IEmployeeDao employeeDao;
    private final OvalValidator<Employee> validator;

    @Override
    public void createOrUpdate(Employee obj) throws ValidateException, CRUDException {
        validator.validate(obj);
        employeeDao.createOrUpdate(obj);
    }

    @Override
    public List<Employee> getAllByEmployeeId(Integer employeeId) throws CRUDException {
        return employeeDao.getAllByEmployeeId(employeeId);
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
