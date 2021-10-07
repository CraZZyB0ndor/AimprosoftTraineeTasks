package com.aimprosoft.dao.impl;

import com.aimprosoft.config.JDBConnectivity;
import com.aimprosoft.dao.IDepartmentDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCDepartmentDao implements IDepartmentDao {

    private static final String CREATE_DEPARTMENT_QUERY = "INSERT INTO Department (name) VALUES (?)";
    private static final String UPDATE_DEPARTMENT_BY_ID_QUERY = "UPDATE Department SET name = ? WHERE departmentId = ?";
    private static final String GET_ALL_DEPARTMENTS_QUERY = "SELECT * FROM Department";
    private static final String DELETE_DEPARTMENT_BY_ID_QUERY = "DELETE FROM Department WHERE departmentId = ?";
    private static final String GET_DEPARTMENT_BY_NAME_QUERY = "SELECT * FROM Department WHERE name = ?";

    @Override
    public void createOrUpdate(Department obj) throws CRUDException {
        // If a department doesn't equal NULL, it exists.
        final String certainQuery = obj.getId() != null ? UPDATE_DEPARTMENT_BY_ID_QUERY : CREATE_DEPARTMENT_QUERY;
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(certainQuery)) {
            preparedStatement.setString(1, obj.getName());
            if (obj.getId() != null) {
                preparedStatement.setInt(2, obj.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new CRUDException("create or update department");
        }
    }

    @Override
    public List<Department> getAll() throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             Statement statement = connection.createStatement()) {
            return getDepartmentListFromResultSet(statement.executeQuery(GET_ALL_DEPARTMENTS_QUERY));
        } catch (SQLException ex) {
            throw new CRUDException("get all departments");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement deleteDepartment = connection.prepareStatement(DELETE_DEPARTMENT_BY_ID_QUERY)) {
            deleteDepartment.setInt(1, id);
            deleteDepartment.executeUpdate();
        } catch (SQLException ex) {
            throw new CRUDException("delete department by id");
        }
    }

    @Override
    public boolean isExistByName(Department department) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENT_BY_NAME_QUERY)) {
            preparedStatement.setString(1, department.getName());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (department.getId() != null) {
                if (resultSet.next()) {
                    final Department tempDepartment = getDepartment(resultSet);
                    return !tempDepartment.getId().equals(department.getId());
                }
                return false;
            }
            return resultSet.next();
        } catch (SQLException ex) {
            throw new CRUDException("is exist department by name");
        }
    }

    private List<Department> getDepartmentListFromResultSet(ResultSet departmentResultSet) throws SQLException {
        final List<Department> departments = new LinkedList<>();
        while (departmentResultSet.next()) {
            departments.add(getDepartment(departmentResultSet));
        }
        return departments;
    }

    private Department getDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt("departmentId"),
                resultSet.getString("name"));
    }
}
