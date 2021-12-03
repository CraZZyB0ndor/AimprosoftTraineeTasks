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
    public Employee createOrUpdate(Employee employee) throws CRUDException {
        try {
            final Session session = sessionFactory.getCurrentSession();
            employee.setDepartment(session.get(Department.class, employee.getDepartment().getId()));
            return (Employee) session.merge(employee);
        } catch (Exception ex) {
            throw new CRUDException("create or update employee");
        }
    }

    @Override
    public List<Employee> getAllByDepartmentId(Integer departmentId) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("FROM Employee  WHERE department.id = :departmentId", Employee.class)
                    .setParameter("departmentId", departmentId).list();
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
    public Employee getByEmail(String email) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("FROM Employee WHERE email = :email", Employee.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception ex) {
            throw new CRUDException("get employee by email");
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
    public Employee getEmployeeByName(String employeeName) throws CRUDException {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("FROM Employee WHERE email = :email", Employee.class)
                    .setCacheable(true)
                    .setParameter("email", employeeName).uniqueResult();
        } catch (Exception ex) {
            throw new CRUDException("is exist employee by id");
        }
    }
}
