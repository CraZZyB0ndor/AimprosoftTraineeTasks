package com.aimprosoft.validation.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsUniqueNameCheck implements CheckWithCheck.SimpleCheck {

    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentService.isExistByName((Department) validatedObject);
        } catch (CRUDException e) {
            return false;
        }
    }
}
