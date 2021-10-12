package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateEmployeeDao implements IEmployeeDao {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createOrUpdate(Employee obj) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
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
    @Transactional
    public List<Employee> getAllByForeignId(Integer otherObjId) throws CRUDException {
        try (final Session session = sessionFactory.openSession()) {
            return session.createQuery("from Employee  WHERE departmentId.id = :departmentId", Employee.class)
                    .setParameter("departmentId", otherObjId).list();
        } catch (Exception ex) {
            throw new CRUDException("get all employees by department id");
        }
    }

    @Override
    @Transactional
    public Employee getById(Integer id) throws CRUDException {
        try (final Session session = sessionFactory.openSession();) {
            return session.get(Employee.class, id);
        } catch (Exception ex) {
            throw new CRUDException("get employee by id");
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
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
    @Transactional
    public boolean isExistByEmail(Employee employee) throws CRUDException {
        try (final Session session = sessionFactory.openSession()) {
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
