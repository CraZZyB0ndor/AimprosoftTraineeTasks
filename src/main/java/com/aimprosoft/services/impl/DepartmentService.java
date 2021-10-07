package com.aimprosoft.services.impl;

import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.dao.impl.HibernateDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.validation.OvalValidator;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    private final IDepartmentDao departmentDao = HibernateDepartmentDao.getDepartmentDao();
    private final OvalValidator<Department> validator = new OvalValidator<>();

    @Override
    public void createOrUpdate(Department obj) throws ValidateException, CRUDException {
        validator.validate(obj);
        departmentDao.createOrUpdate(obj);
    }

    @Override
    public List<Department> getAll() throws CRUDException {
        return departmentDao.getAll();
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        departmentDao.deleteById(id);
    }

    @Override
    public boolean isExistByName(Department department) throws CRUDException {
        return departmentDao.isExistByName(department);
    }
}
