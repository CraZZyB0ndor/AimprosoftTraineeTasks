package com.aimprosoft.exceptions.handler;

import com.aimprosoft.exceptions.CRUDException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CRUDException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "CRUD exception has been occurred!")
    @ResponseBody
    public ResponseEntity<String> defaultErrorHandler(CRUDException crudException) {
        return new ResponseEntity<>(crudException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
