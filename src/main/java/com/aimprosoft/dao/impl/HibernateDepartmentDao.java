package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
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
public class HibernateDepartmentDao implements IDepartmentDao {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createOrUpdate(Department department) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CRUDException("create or update department");
        }
    }

    @Override
    @Transactional
    public List<Department> getAll() throws CRUDException {
        try (final Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
        } catch (Exception ex) {
            throw new CRUDException("get all departments");
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.load(Department.class, id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CRUDException("delete department by id");
        }
    }

    @Override
    @Transactional
    public boolean isExistByName(Department department) throws CRUDException {
        try (final Session session = sessionFactory.openSession()) {
            final Department departmentFromDb = session.createQuery("FROM Department WHERE name = :name", Department.class)
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
