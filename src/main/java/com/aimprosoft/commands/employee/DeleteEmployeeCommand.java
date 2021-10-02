package com.aimprosoft.commands.employee;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.EmployeeService;
import com.aimprosoft.utils.RequestUtils;
import org.codehaus.httpcache4j.uri.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand implements FrontCommand {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, CRUDException {

        employeeService.deleteById(RequestUtils.getInt(request.getParameter("id")));
        response.sendRedirect(URIBuilder.fromString("")
                .addPath("displayEmployees")
                .addParameter("departmentId",
                        request.getParameter("departmentId")).toURI().toString());
    }
}
