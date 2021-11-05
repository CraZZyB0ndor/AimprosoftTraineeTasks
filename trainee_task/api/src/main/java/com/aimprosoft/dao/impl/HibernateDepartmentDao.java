package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = CRUDException.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateDepartmentDao implements IDepartmentDao {

    private final SessionFactory sessionFactory;

    @Override
    public void createOrUpdate(Department department) throws CRUDException {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(department);
        } catch (Exception ex) {
            throw new CRUDException("create or update department");
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession().get(Department.class, id);
        } catch (Exception ex) {
            throw new CRUDException("get department by ID");
        }
    }

    @Override
    public List<Department> getAll() throws CRUDException {
        try {
            return sessionFactory.getCurrentSession().createQuery("FROM Department", Department.class).list();
        } catch (Exception ex) {
            throw new CRUDException("get all departments");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        try {
            final Session session = sessionFactory.getCurrentSession();
            session.delete(session.load(Department.class, id));
        } catch (Exception ex) {
            throw new CRUDException("delete department by id");
        }
    }

    @Override
    public boolean isExistByName(Department department) throws CRUDException {
        try {
            final Department departmentFromDb = sessionFactory.getCurrentSession()
                    .createQuery("FROM Department WHERE name = :name", Department.class)
                    .setCacheable(true)
                    .setParameter("name", department.getName()).uniqueResult();
            if (department.getId() != null && departmentFromDb != null) {
                return !departmentFromDb.getId().equals(department.getId());
            }
            return departmentFromDb != null;
        } catch (Exception ex) {
            throw new CRUDException("is exist department by name");
        }
    }
}
