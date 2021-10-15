package com.aimprosoft.services.impl;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.commands.department.DepartmentEditFormCommand;
import com.aimprosoft.commands.department.DisplayDepartmentsCommand;
import com.aimprosoft.commands.department.UpdateOrCreateDepartmentCommand;
import com.aimprosoft.commands.employee.DeleteEmployeeCommand;
import com.aimprosoft.commands.employee.DisplayEmployeeCommand;
import com.aimprosoft.commands.employee.EmployeeEditFormCommand;
import com.aimprosoft.commands.employee.UpdateOrCreateEmployeeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandFactory {

    private Map<String, FrontCommand> commands;
    private DisplayDepartmentsCommand displayDepartmentsCommand;

    @Autowired
    public void setCommandFactory(DisplayDepartmentsCommand displayDepartmentsCommand,
                                  DepartmentEditFormCommand departmentEditFormCommand,
                                  UpdateOrCreateDepartmentCommand updateOrCreateDepartmentCommand,
                                  DeleteDepartmentCommand deleteDepartmentCommand,
                                  DisplayEmployeeCommand displayEmployeeCommand,
                                  EmployeeEditFormCommand employeeEditFormCommand,
                                  UpdateOrCreateEmployeeCommand updateOrCreateEmployeeCommand,
                                  DeleteEmployeeCommand deleteEmployeeCommand) {
        commands = new HashMap<>();
        commands.put("/displayDepartments", displayDepartmentsCommand);
        commands.put("/openDepartmentForm", departmentEditFormCommand);
        commands.put("/createOrEditDepartment", updateOrCreateDepartmentCommand);
        commands.put("/deleteDepartment", deleteDepartmentCommand);
        commands.put("/displayEmployees", displayEmployeeCommand);
        commands.put("/openEmployeeForm", employeeEditFormCommand);
        commands.put("/createOrEditEmployee", updateOrCreateEmployeeCommand);
        commands.put("/deleteEmployee", deleteEmployeeCommand);
        this.displayDepartmentsCommand = displayDepartmentsCommand;
    }

    public FrontCommand getCommand(String operation) {
        return commands.getOrDefault(operation, displayDepartmentsCommand);
    }
}
