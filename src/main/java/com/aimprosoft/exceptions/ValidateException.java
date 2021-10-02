package com.aimprosoft.exceptions;

import java.util.*;

public class ValidateException extends Exception {

    private final Map<String, List<String>> errors;

    public ValidateException(Map<String, List<String>> errorsMap) {
        this.errors = errorsMap;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
