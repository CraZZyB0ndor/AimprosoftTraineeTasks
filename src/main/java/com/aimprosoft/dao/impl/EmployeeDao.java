package com.aimprosoft.dao.impl;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public final class EmployeeDao implements IEmployeeDao {

    private static final SessionFactory factory = HibernateSessionFactory.getSessionFactory();

    public static EmployeeDao getEmployeeDao() {
        return new EmployeeDao();
    }

    private EmployeeDao() {
    }

    @Override
    public void createOrUpdate(Employee obj) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            obj.setDepartmentId(session.get(Department.class, obj.getDepartmentId().getId()));
            if (obj.getId() == null) {
                session.persist(obj);
            } else {
                session.update(obj);
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CRUDException("create or update employee");
        }
    }

    @Override
    public List<Employee> getAllByForeignId(Integer otherObjId) throws CRUDException {
        try (final Session session = factory.openSession()) {
            return session.createQuery("from Employee  WHERE departmentId.id = :departmentId", Employee.class)
                    .setParameter("departmentId", otherObjId).list();
        } catch (Exception ex) {
            throw new CRUDException("get all employees by department id");
        }
    }

    @Override
    public Employee getById(Integer id) throws CRUDException {
        try (final Session session = factory.openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception ex) {
            throw new CRUDException("get employee by id");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(Employee.class, id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CRUDException("delete employee by id");
        }
    }

    @Override
    public boolean isExistByEmail(Employee employee) throws CRUDException {
        try (final Session session = factory.openSession()) {
            final Employee employeeFromDb = session.createQuery("FROM Employee WHERE email = :email", Employee.class)
                    .setParameter("email", employee.getEmail()).uniqueResult();
            if (employee.getId() != null) {
                if (employeeFromDb != null) {
                    return !employeeFromDb.getId().equals(employee.getId());
                }
            }
            return employeeFromDb != null;
        } catch (Exception ex) {
            throw new CRUDException("is exist employee by id");
        }
    }
}
