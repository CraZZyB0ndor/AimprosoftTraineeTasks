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

    @GetMapping("/employee")
    public Department displayEmployees(@RequestParam final Integer departmentId) throws CRUDException {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/employee")
    public ResponseEntity<Map<String, List<String>>> createOrEditEmployee(@RequestBody final Employee employee)
            throws CRUDException {
        try {
            employeeService.createOrUpdate(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidateException validateException) {
            return new ResponseEntity<>(validateException.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/employee")
    public void deleteEmployee(@RequestParam(name = "id") final Integer id) throws CRUDException {
        employeeService.deleteById(id);
    }

}
