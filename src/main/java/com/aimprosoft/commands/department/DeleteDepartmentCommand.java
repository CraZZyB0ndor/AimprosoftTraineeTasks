package com.aimprosoft.commands.department;

import com.aimprosoft.commands.FrontCommand;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.DepartmentService;
import com.aimprosoft.utils.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteDepartmentCommand implements FrontCommand {

    private final DepartmentService departmentService;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, CRUDException {

        departmentService.deleteById(RequestUtils.getInt(request.getParameter("departmentId")));
        response.sendRedirect("displayDepartments");
    }
}
