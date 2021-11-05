package com.aimprosoft.exceptions.handler;

import com.aimprosoft.exceptions.CRUDException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CRUDException.class)
    public ModelAndView defaultErrorHandler(CRUDException crudException) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", crudException.getMessage());
        mav.setViewName("CRUDErrorPage");
        return mav;
    }
}
