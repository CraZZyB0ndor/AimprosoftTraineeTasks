package com.aimprosoft.controllers.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping(value = {"/department", "/*"})
    public List<Department> displayDepartments() throws CRUDException {
        return departmentService.getAll();
    }

    @PostMapping("/department")
    public ResponseEntity<Map<String, List<String>>> createOrEditDepartments(@RequestBody final Department department)
            throws CRUDException {
        try {
            departmentService.createOrUpdate(department);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidateException validateException) {
            return new ResponseEntity<>(validateException.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/department")
    public void deleteDepartment(@RequestParam final Integer id) throws CRUDException {
        departmentService.deleteById(id);
    }
}
