package com.aimprosoft.controllers.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping(value = {"/api/departments", "/*"})
    public List<Department> displayDepartments() throws CRUDException {
        return departmentService.getAll();
    }

    @GetMapping("/api/departments/id/{id}")
    public Department getDepartmentById(@PathVariable final Integer id) throws CRUDException {
        return departmentService.getById(id);
    }

    @GetMapping("/api/departments/exist")
    public Department getDepartmentByName(@RequestParam final String departmentName) throws CRUDException {
        return departmentService.getDepartmentByName(departmentName);
    }

    @PostMapping("/api/departments")
    public Department createOrEditDepartments(@RequestBody final Department department)
            throws CRUDException, ValidateException {
        return departmentService.createOrUpdate(department);
    }

    @DeleteMapping("/api/departments")
    public void deleteDepartment(@RequestBody final Department department) throws CRUDException {
        departmentService.deleteById(department.getId());
    }
}
