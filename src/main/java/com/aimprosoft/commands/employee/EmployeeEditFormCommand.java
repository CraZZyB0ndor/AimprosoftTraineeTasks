package com.aimprosoft.commands.employee;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.impl.EmployeeService;
import com.aimprosoft.utils.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeEditFormCommand implements FrontCommand {

    private final EmployeeService employeeService;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CRUDException {
        request.setAttribute("employee", getEmployee(request));
        request.getRequestDispatcher("/WEB-INF/pages/forms/createOrEditEmployeeForm.jsp").forward(request, response);
    }

    private Employee getEmployee(HttpServletRequest request) throws CRUDException {
        final Integer employeeId = RequestUtils.getInt(request.getParameter("id"));
        if (employeeId != null) {
            return employeeService.getById(employeeId);
        }
        return new Employee().withDepartmentId(new Department()
                .withId(RequestUtils.getInt(request.getParameter("departmentId"))));
    }
}
