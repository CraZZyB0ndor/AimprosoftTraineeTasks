package com.aimprosoft.commands.department;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.impl.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayDepartmentsCommand implements FrontCommand {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CRUDException {

        final List<Department> departments = departmentService.getAll();
        request.setAttribute("departments", departments);
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/department.jsp").forward(request, response);
    }
}
