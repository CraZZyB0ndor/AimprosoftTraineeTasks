package com.aimprosoft.validation;

import com.aimprosoft.exceptions.ValidateException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OvalValidator<T> {

    private final Validator validator = new Validator();

    public void validate(T obj) throws ValidateException {
        final List<ConstraintViolation> errors = validator.validate(obj);
        if (!errors.isEmpty()) {
            Map<String, List<String>> errorsMap = errors.stream().collect(Collectors.groupingBy(item ->
                            item.getCheckDeclaringContext().toStringUnqualified(),
                    Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
            throw new ValidateException(errorsMap);
        }
    }
}
