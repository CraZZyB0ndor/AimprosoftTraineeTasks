package com.aimprosoft.commands.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.services.impl.DepartmentService;
import com.aimprosoft.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateOrCreateDepartmentCommand implements FrontCommand {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            CRUDException {

        final Department department = getDepartment(request);
        try {
            departmentService.createOrUpdate(department);
            response.sendRedirect("displayDepartments");
        } catch (ValidateException e) {
            request.setAttribute("errors", e.getErrors());
            request.setAttribute("department", department);
            request.getRequestDispatcher("/WEB-INF/pages/forms/createOrEditDepartmentForm.jsp")
                    .forward(request, response);
        }
    }

    private Department getDepartment(HttpServletRequest request) {
        return new Department(RequestUtils.getInt(request.getParameter("id")),
                request.getParameter("name"));
    }
}
