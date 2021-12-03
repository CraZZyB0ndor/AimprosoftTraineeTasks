package com.aimprosoft.services.impl;

import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.validation.OvalValidator;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService implements IDepartmentService {

    private final IDepartmentDao departmentDao;
    private final OvalValidator<Department> validator;

    @Override
    public Department createOrUpdate(Department department) throws CRUDException {
        try {
            validator.validate(department);
            return departmentDao.createOrUpdate(department);
        } catch (ValidateException ex) {
            return null;
        }
    }

    @Override
    public Department getById(Integer id) throws CRUDException {
        return departmentDao.getById(id);
    }

    @Override
    public List<Department> getAll() throws CRUDException {
        return departmentDao.getAll();
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws CRUDException {
        return departmentDao.getDepartmentByName(departmentName);
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        departmentDao.deleteById(id);
    }
}
