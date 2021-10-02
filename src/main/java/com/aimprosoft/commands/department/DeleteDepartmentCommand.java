package com.aimprosoft.commands.department;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.DepartmentService;
import com.aimprosoft.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentCommand implements FrontCommand {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, CRUDException {

        departmentService.deleteById(RequestUtils.getInt(request.getParameter("departmentId")));
        response.sendRedirect("displayDepartments");
    }
}
