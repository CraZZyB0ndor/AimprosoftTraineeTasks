package com.aimprosoft.commands.employee;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.impl.DepartmentService;
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
public class DisplayEmployeeCommand implements FrontCommand {

    private final DepartmentService departmentService;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CRUDException {

        final Integer departmentId = RequestUtils.getInt(request.getParameter("departmentId"));
        final Department department = departmentService.getDepartmentById(departmentId);
        request.setAttribute("employees", department.getEmployees());
        request.setAttribute("departmentId", departmentId);
        request.getRequestDispatcher("/WEB-INF/pages/employee.jsp").forward(request, response);
    }
}
