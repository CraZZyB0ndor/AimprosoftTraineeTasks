package com.aimprosoft.commands.employee;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.impl.EmployeeService;
import com.aimprosoft.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayEmployeeCommand implements FrontCommand {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CRUDException {

        final Integer departmentId = RequestUtils.getInt(request.getParameter("departmentId"));
        final List<Employee> employeesLinkedList = employeeService.getAllByForeignId(departmentId);
        request.setAttribute("employees", employeesLinkedList);
        request.setAttribute("departmentId", departmentId);
        request.getRequestDispatcher("/WEB-INF/pages/employee.jsp").forward(request, response);
    }
}
