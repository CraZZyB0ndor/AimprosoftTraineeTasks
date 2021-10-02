package com.aimprosoft.services.impl;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.commands.department.DisplayDepartmentsCommand;
import com.aimprosoft.commands.department.UpdateOrCreateDepartmentCommand;
import com.aimprosoft.commands.department.DepartmentEditFormCommand;
import com.aimprosoft.commands.employee.DeleteEmployeeCommand;
import com.aimprosoft.commands.employee.DisplayEmployeeCommand;
import com.aimprosoft.commands.employee.UpdateOrCreateEmployeeCommand;
import com.aimprosoft.commands.employee.EmployeeEditFormCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final Map<String, FrontCommand> commands;

    {
        commands = new HashMap<>();
        commands.put("/displayDepartments", new DisplayDepartmentsCommand());
        commands.put("/openDepartmentForm", new DepartmentEditFormCommand());
        commands.put("/createOrEditDepartment", new UpdateOrCreateDepartmentCommand());
        commands.put("/deleteDepartment", new DeleteDepartmentCommand());
        commands.put("/displayEmployees", new DisplayEmployeeCommand());
        commands.put("/openEmployeeForm", new EmployeeEditFormCommand());
        commands.put("/createOrEditEmployee", new UpdateOrCreateEmployeeCommand());
        commands.put("/deleteEmployee", new DeleteEmployeeCommand());
    }

    public FrontCommand getCommand(String operation) {
        return commands.getOrDefault(operation, new DisplayDepartmentsCommand());
    }
}
