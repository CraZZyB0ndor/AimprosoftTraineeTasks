package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateEmployeeDao implements IEmployeeDao {

    private final SessionFactory sessionFactory;

    @Override
    public void createOrUpdate(Employee obj) throws CRUDException {
        try {
            final Session session = sessionFactory.getCurrentSession();
            obj.setDepartmentId(session.get(Department.class, obj.getDepartmentId().getId()));
            session.saveOrUpdate(obj);
        } catch (Exception ex) {
            throw new CRUDException("create or update employee");
        }
    }

    @Override
    public List<Employee> getAllByEmployeeId(Integer employeeId) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Employee  WHERE departmentId.id = :departmentId", Employee.class)
                    .setParameter("departmentId", employeeId).list();
        } catch (Exception ex) {
            throw new CRUDException("get all employees by department id");
        }
    }

    @Override
    public Employee getById(Integer id) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession().get(Employee.class, id);
        } catch (Exception ex) {
            throw new CRUDException("get employee by id");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(Employee.class, id));
        } catch (Exception ex) {
            throw new CRUDException("delete employee by id");
        }
    }

    @Override
    public boolean isExistByEmail(Employee employee) throws CRUDException {
        try {
            final Employee employeeFromDb = sessionFactory.getCurrentSession()
                    .createQuery("FROM Employee WHERE email = :email", Employee.class)
                    .setCacheable(true)
                    .setParameter("email", employee.getEmail()).uniqueResult();
            if (employee.getId() != null && employeeFromDb != null) {
                return !employeeFromDb.getId().equals(employee.getId());
            }
            return employeeFromDb != null;
        } catch (Exception ex) {
            throw new CRUDException("is exist employee by id");
        }
    }
}
