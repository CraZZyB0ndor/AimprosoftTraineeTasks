package com.aimprosoft.controllers.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String openDepartmentForm(@ModelAttribute final Department department, Model model) {
        model.addAttribute("department", department);
        return "forms/createOrEditDepartmentForm";
    }

    @PostMapping("/createOrEditDepartment")
    public String createOrEditDepartments(@ModelAttribute final Department department, Model model) throws CRUDException {
        try {
            departmentService.createOrUpdate(department);
            return "redirect:department";
        } catch (ValidateException e) {
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("department", department);
            return "forms/createOrEditDepartmentForm";
        }
    }

    @PostMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam final Integer id) throws CRUDException {
        departmentService.deleteById(id);
        return "redirect:displayDepartments";
    }
}
