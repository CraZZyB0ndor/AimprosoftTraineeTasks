package com.aimprosoft.commands.department;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.models.Department;
import com.aimprosoft.utils.RequestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DepartmentEditFormCommand implements FrontCommand {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final Department department = getDepartment(request);
        request.setAttribute("department", department);
        request.getRequestDispatcher("/WEB-INF/pages/forms/createOrEditDepartmentForm.jsp").forward(request, response);
    }

    private Department getDepartment(HttpServletRequest request) {
        return new Department(RequestUtils.getInt(request.getParameter("id")),
                request.getParameter("name"));
    }
}
