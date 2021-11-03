package com.aimprosoft.controllers.employee;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.exceptions.ValidateException;
import com.aimprosoft.models.Department;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.services.IEmployeeService;
import com.aimprosoft.utils.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    public final IDepartmentService departmentService;
    public final IEmployeeService employeeService;

    @GetMapping("/displayEmployees")
    @ResponseBody
    public Department displayEmployees(@RequestParam(name = "departmentId") final Integer departmentId)
            throws CRUDException {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/openEmployeeForm")
    @ResponseBody
    public Employee openEmployeeForm(@RequestBody final Employee employee) throws CRUDException {
        return employee.getId() != null ? employeeService.getById(employee.getId()) : employee;
    }

    /*
    @PostMapping("/createOrEditEmployee")
    @ResponseBody
    public Map<String, List<String>> createOrEditEmployee(@RequestBody final Employee employee) throws CRUDException {
        try {
            employeeService.createOrUpdate(employee);
            model.addAttribute("departmentId", employee.getDepartment().getId());
            return "redirect:displayEmployees";
        } catch (ValidateException e) {
            employee.setStartWorkingDate(RequestUtils.getEmployeeWithCorrectDate(employee));
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("employee", employee);
            return "forms/createOrEditEmployeeForm";
        }
    }

     */

    @PostMapping(value = "/deleteEmployee")
    public void deleteEmployee(@RequestParam(name = "id") final Integer id,
                               @RequestParam(name = "departmentId") final Integer departmentId) throws CRUDException {
        employeeService.deleteById(id);
    }
}
