package com.aimprosoft.commands.employee;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Employee;
import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.services.impl.EmployeeService;
import com.aimprosoft.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateOrCreateEmployeeCommand implements FrontCommand {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CRUDException {

        final Employee employee = getEmployee(request);
        try {
            employeeService.createOrUpdate(employee);
            response.sendRedirect("displayEmployees" + "?departmentId=" + employee.getDepartmentId());
        } catch (ValidateException e) {
            request.setAttribute("errors", e.getErrors());
            request.setAttribute("employee", employee);
            request.getServletContext().getRequestDispatcher("/WEB-INF/pages/forms/createOrEditEmployeeForm.jsp")
                    .forward(request, response);
        }
    }

    private Employee getEmployee(HttpServletRequest request) {
        return new Employee().withId(RequestUtils.getInt(request.getParameter("id")))
                .withEmail(request.getParameter("email"))
                .withDepartmentId(RequestUtils.getInt(request.getParameter("departmentId")))
                .withName(request.getParameter("name"))
                .withAge(RequestUtils.getInt(request.getParameter("age")))
                .withStartWorkingDate(RequestUtils.getDate(request.getParameter("startWorkingDate")));
    }
}
