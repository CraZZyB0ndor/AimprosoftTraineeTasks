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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    public final IDepartmentService departmentService;
    public final IEmployeeService employeeService;

    @GetMapping("/displayEmployees")
    public String displayEmployees(@RequestParam(name = "departmentId") final Integer departmentId, Model model)
            throws CRUDException {
        final Department department = departmentService.getDepartmentById(departmentId);
        model.addAttribute("employees", department.getEmployees());
        model.addAttribute("departmentId", departmentId);
        return "employee";
    }

    @PostMapping("/openEmployeeForm")
    public String openEmployeeForm(@ModelAttribute final Employee employee, Model model) throws CRUDException {
        model.addAttribute("employee", employee.getId() != null ?
                employeeService.getById(employee.getId()) : employee);
        return "forms/createOrEditEmployeeForm";
    }

    @PostMapping("/createOrEditEmployee")
    public String createOrEditEmployee(@ModelAttribute final Employee employee, Model model) throws CRUDException {
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

    @PostMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam(name = "id") final Integer id,
                                 @RequestParam(name = "departmentId") final Integer departmentId,
                                 Model model) throws CRUDException {
        employeeService.deleteById(id);
        model.addAttribute("departmentId", departmentId);
        return "redirect:displayEmployees";
    }
}
