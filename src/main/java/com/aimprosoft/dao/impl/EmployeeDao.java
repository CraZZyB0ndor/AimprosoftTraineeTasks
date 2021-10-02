package com.aimprosoft.dao.impl;

import com.aimprosoft.config.JDBConnectivity;
import com.aimprosoft.dao.IEmployeeDao;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public final class EmployeeDao implements IEmployeeDao {

    private static final String CREATE_EMPLOYEE_QUERY = "INSERT INTO Employee" +
            " (name, email, age, startWorkingDate, departmentId) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE Employee SET name = (?), email = (?), age = (?), " +
            "startWorkingDate = (?) WHERE employeeId = (?)";
    private static final String DELETE_EMPLOYEE_BY_ID_QUERY = "DELETE FROM Employee WHERE employeeId = (?)";
    private static final String GET_ALL_EMPLOYEE_BY_DEPARTMENT_ID_QUERY = "SELECT * FROM Employee WHERE departmentId = (?)";
    private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM Employee WHERE employeeId = (?)";
    private static final String GET_EMPLOYEE_BY_EMAIL_QUERY = "SELECT * FROM Employee WHERE email = (?)";

    @Override
    public void createOrUpdate(Employee obj) throws CRUDException {
        // If a department doesn't equal NULL, it exists.
        final String certainQuery = obj.getId() != null ? UPDATE_EMPLOYEE_QUERY : CREATE_EMPLOYEE_QUERY;

        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(certainQuery)) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setInt(3, obj.getAge());
            preparedStatement.setDate(4, new Date(obj.getStartWorkingDate().getTime()));
            if (obj.getId() != null) {
                preparedStatement.setInt(5, obj.getId());
            } else {
                preparedStatement.setInt(5, obj.getDepartmentId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new CRUDException("create or update employee");
        }
    }

    @Override
    public List<Employee> getAllByForeignId(Integer otherObjId) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_BY_DEPARTMENT_ID_QUERY)) {
            preparedStatement.setInt(1, otherObjId);
            return getEmployeesListFromResultSet(preparedStatement.executeQuery());
        } catch (Exception ex) {
            throw new CRUDException("get all employees by department id");
        }
    }

    @Override
    public Employee getById(Integer id) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getEmployee(resultSet);
        } catch (SQLException ex) {
            throw new CRUDException("get employee by id");
        }
    }

    @Override
    public void deleteById(Integer id) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new CRUDException("delete employee by id");
        }
    }

    @Override
    public boolean isExistByEmail(Employee employee) throws CRUDException {
        try (Connection connection = JDBConnectivity.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, employee.getEmail());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (employee.getId() != null) {
                if (resultSet.next()) {
                    final Employee tempEmployee = getEmployee(resultSet);
                    return !tempEmployee.getId().equals(employee.getId());
                }
                return false;
            }
            return resultSet.next();
        } catch (SQLException ex) {
            throw new CRUDException("is exist employee by id");
        }
    }

    private List<Employee> getEmployeesListFromResultSet(ResultSet employeesResultSet) throws SQLException {
        final List<Employee> employees = new LinkedList<>();
        while (employeesResultSet.next()) {
            employees.add(getEmployee(employeesResultSet));
        }
        return employees;
    }

    private Employee getEmployee(ResultSet employeeResultSet) throws SQLException {
        return new Employee().withId(employeeResultSet.getInt("employeeId"))
                .withEmail(employeeResultSet.getString("email"))
                .withDepartmentId(employeeResultSet.getInt("departmentId"))
                .withName(employeeResultSet.getString("name"))
                .withAge(employeeResultSet.getInt("age"))
                .withStartWorkingDate(employeeResultSet.getDate("startWorkingDate"));
    }
}
