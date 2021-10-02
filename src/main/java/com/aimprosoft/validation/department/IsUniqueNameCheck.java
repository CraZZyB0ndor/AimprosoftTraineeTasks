package com.aimprosoft.validation.department;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.models.Department;
import com.aimprosoft.services.IDepartmentService;
import com.aimprosoft.services.impl.DepartmentService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;

public class IsUniqueNameCheck implements CheckWithCheck.SimpleCheck {

    private final IDepartmentService departmentService = new DepartmentService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentService.isExistByName((Department) validatedObject);
        } catch (CRUDException e) {
            return false;
        }
    }
}
