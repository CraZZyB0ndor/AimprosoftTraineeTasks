package com.aimprosoft.validation.employee;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Employee;
import com.aimprosoft.services.IEmployeeService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsUniqueEmailCheck implements CheckWithCheck.SimpleCheck {

    private IEmployeeService employeeService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            final Employee employee = (Employee) validatedObject;
            final Employee employeeDb = employeeService.getEmployeeByName(employee.getName());
            if (employee.getId() != null && employeeDb != null) {
                return employeeDb.getId().equals(employee.getId());
            }
            return employeeDb == null;
        } catch (CRUDException e) {
            return false;
        }
    }
}
