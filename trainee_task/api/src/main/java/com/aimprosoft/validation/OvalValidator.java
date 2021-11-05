package com.aimprosoft.validation;

import com.aimprosoft.exceptions.ValidateException;
import lombok.AllArgsConstructor;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OvalValidator<T>  {

    private final Validator ovalValidator;

    public void validate(T obj) throws ValidateException {
        final List<ConstraintViolation> errors = ovalValidator.validate(obj);
        if (!errors.isEmpty()) {
            Map<String, List<String>> errorsMap = errors.stream().collect(Collectors.groupingBy(item ->
                            item.getCheckDeclaringContext().toStringUnqualified(),
                    Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
            throw new ValidateException(errorsMap);
        }
    }
}
