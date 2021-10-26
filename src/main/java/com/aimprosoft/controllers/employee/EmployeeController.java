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
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    public final IDepartmentService departmentService;
    public final IEmployeeService employeeService;

    @GetMapping("/displayEmployees")
    public String displayEmployees(HttpServletRequest request, Model model) throws CRUDException {
        final Integer departmentId = RequestUtils.getInt(request.getParameter("departmentId"));
        final Department department = departmentService.getDepartmentById(departmentId);
        model.addAttribute("employees", department.getEmployees());
        model.addAttribute("departmentId", departmentId);
        return "employee";
    }

    @GetMapping("/openEmployeeForm")
    public String openEmployeeForm(HttpServletRequest request, Model model) throws CRUDException {
        model.addAttribute("employee", getEmployeeInfoFromDb(request));
        return "forms/createOrEditEmployeeForm";
    }

    @PostMapping(value = "/createOrEditEmployee")
    public String createOrEditEmployee(HttpServletRequest request, Model model) throws CRUDException {
        final Employee employee = getEmployeeInfoFromWebPage(request);
        try {
            employeeService.createOrUpdate(employee);
            model.addAttribute("departmentId", employee.getDepartment().getId());
            return "redirect:displayEmployees";
        } catch (ValidateException e) {
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("employee", employee);
            return "forms/createOrEditEmployeeForm";
        }
    }

    @PostMapping(value = "/deleteEmployee")
    public String deleteEmployee(HttpServletRequest request, Model model) throws CRUDException {
        employeeService.deleteById(RequestUtils.getInt(request.getParameter("id")));
        model.addAttribute("departmentId", request.getParameter("departmentId"));
        return "redirect:displayEmployees";
    }

    private Employee getEmployeeInfoFromDb(HttpServletRequest request) throws CRUDException {
        final Integer departmentId = RequestUtils.getInt(request.getParameter("departmentId"));
        final Integer employeeId = RequestUtils.getInt(request.getParameter("id"));
        return employeeId != null ? employeeService.getById(employeeId) :
                new Employee().withDepartment(new Department().withId(departmentId));
    }

    private Employee getEmployeeInfoFromWebPage(HttpServletRequest request) {
        return new Employee().withId(RequestUtils.getInt(request.getParameter("id")))
                .withEmail(request.getParameter("email"))
                .withDepartment(new Department().withId(RequestUtils.getInt(request.getParameter("departmentId"))))
                .withName(request.getParameter("name"))
                .withAge(RequestUtils.getInt(request.getParameter("age")))
                .withStartWorkingDate(RequestUtils.getDate(request.getParameter("startWorkingDate")));
    }
}
