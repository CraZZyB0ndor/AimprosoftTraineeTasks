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
            return !employeeService.isExistByEmail((Employee) validatedObject);
        } catch (CRUDException e) {
            return false;
        }
    }
}
