package com.aimprosoft.controllers.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.utils.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping(value = {"/displayDepartments", "/*"})
    public String displayDepartments(Model model)
            throws CRUDException {
        model.addAttribute("departments", departmentService.getAll());
        return "department";
    }

    @GetMapping("/openDepartmentForm")
    public String openDepartmentForm(HttpServletRequest request, Model model) {
        model.addAttribute("department", getDepartment(request));
        return "forms/createOrEditDepartmentForm";
    }

    @PostMapping(value = "/createOrEditDepartment")
    public String createOrEditDepartments(HttpServletRequest request, Model model) throws CRUDException {
        final Department department = getDepartment(request);
        try {
            departmentService.createOrUpdate(department);
            return "redirect:department";
        } catch (ValidateException e) {
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("department", department);
            return "forms/createOrEditDepartmentForm";
        }
    }

    @PostMapping(value = "/deleteDepartment")
    public String deleteDepartment(HttpServletRequest request) throws CRUDException {
        departmentService.deleteById(RequestUtils.getInt(request.getParameter("departmentId")));
        return "redirect:displayDepartments";
    }

    private Department getDepartment(HttpServletRequest request) {
        return new Department().withId(RequestUtils.getInt(request.getParameter("id")))
                .withName(request.getParameter("name"));
    }
}
