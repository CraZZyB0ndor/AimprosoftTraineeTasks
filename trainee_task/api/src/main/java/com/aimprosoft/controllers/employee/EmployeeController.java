package com.aimprosoft.controllers.employee;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.services.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    public final IDepartmentService departmentService;
    public final IEmployeeService employeeService;

    @GetMapping("/api/employees/{departmentId}")
    public Department displayEmployees(@PathVariable final Integer departmentId) throws CRUDException {
        return departmentService.getById(departmentId);
    }

    @GetMapping("/api/employees/id/{id}")
    public Employee getEmployeeById(@PathVariable final Integer id) throws CRUDException {
        return employeeService.getById(id);
    }

    @GetMapping("/api/employees/exist")
    public Employee getEmployeeByEmail(@RequestParam final String email) throws CRUDException {
        return employeeService.getByEmail(email);
    }

    @PostMapping("/api/employees")
    public Employee createOrEditEmployee(@RequestBody final Employee employee)
            throws CRUDException, ValidateException {
        return employeeService.createOrUpdate(employee);
    }

    @DeleteMapping("/api/employees")
    public void deleteEmployee(@RequestBody final Employee employee) throws CRUDException {
        employeeService.deleteById(employee.getId());
    }

}
