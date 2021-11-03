package com.aimprosoft.controllers.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping(value = {"/displayDepartments", "/*"})
    @ResponseBody
    public List<Department> displayDepartments() throws CRUDException {
        return departmentService.getAll();
    }

    @GetMapping("/openDepartmentForm")
    @ResponseBody
    public Department openDepartmentForm(@RequestBody final Department department) {
        return department;
    }

    @PostMapping("/createOrEditDepartment")
    @ResponseBody
    public Map<String, List<String>> createOrEditDepartments(@RequestBody final Department department) throws CRUDException {
        try {
            departmentService.createOrUpdate(department);
            return null;
        } catch (ValidateException e) {
            return e.getErrors();
        }
    }

    @PostMapping("/deleteDepartment")
    public void deleteDepartment(@RequestBody final Integer id) throws CRUDException {
        departmentService.deleteById(id);
    }
}
