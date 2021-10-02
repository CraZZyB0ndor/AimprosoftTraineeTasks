package com.aimprosoft.commands;

import com.aimprosoft.exceptions.CRUDException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrontCommand {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            CRUDException;
}
