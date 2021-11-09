package com.aimprosoft.exceptions.handler;

import com.aimprosoft.exceptions.CRUDException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CRUDException.class)
    @ResponseBody
    public String defaultErrorHandler(CRUDException crudException) {
        return crudException.getMessage();
    }
}
