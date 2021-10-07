package com.aimprosoft.dao.impl;

import com.aimprosoft.config.HibernateSessionFactory;
import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public final class HibernateDepartmentDao implements IDepartmentDao {

    private static final SessionFactory factory = HibernateSessionFactory.getSessionFactory();

    public static HibernateDepartmentDao getDepartmentDao() {
        return new HibernateDepartmentDao();
    }

    private HibernateDepartmentDao() {
    }

    @Override
    public void createOrUpdate(Department department) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = factory.openSession()) {
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
    public List<Department> getAll() throws CRUDException {
        try (final Session session = factory.openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
        } catch (Exception ex) {
            throw new CRUDException("get all departments");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        Transaction transaction = null;
        try (final Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(Department.class, id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CRUDException("delete department by id");
        }
    }

    @Override
    public boolean isExistByName(Department department) throws CRUDException {
        try (final Session session = factory.openSession()) {
            final Department departmentFromDb = session.createQuery("FROM Department WHERE name = :name", Department.class)
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
