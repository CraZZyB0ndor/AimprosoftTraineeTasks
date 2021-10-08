package com.aimprosoft.dao.impl;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HibernateEmployeeDao implements IEmployeeDao {

    private static final SessionFactory factory = HibernateSessionFactory.getSessionFactory();

    public static HibernateEmployeeDao getEmployeeDao() {
        return new HibernateEmployeeDao();
    }

    private HibernateEmployeeDao() {
    }

    @Override
    public void createOrUpdate(Employee obj) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            obj.setDepartmentId(session.get(Department.class, obj.getDepartmentId().getId()));
            session.saveOrUpdate(obj);
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
        try (final Session session = factory.openSession();) {
            //Map<String, Object> properties = new HashMap<>();
            //properties.put("java.persistence.fetchgraph", session.getEntityGraph("employee-entity-graph"));
            //Employee employee = session.find(Employee.class, id, properties);// get(Employee.class, id);
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
