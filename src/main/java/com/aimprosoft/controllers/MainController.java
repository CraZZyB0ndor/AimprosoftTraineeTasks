package com.aimprosoft.controllers;

import com.aimprosoft.commands.FrontCommand;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainController extends HttpServlet {

    private final CommandFactory commandFactory = new CommandFactory();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final FrontCommand command = commandFactory.getCommand(req.getRequestURI());
        try {
            command.process(req, resp);
        } catch (CRUDException crudException) {
            req.setAttribute("errorMessage", crudException.getMessage());
            req.getRequestDispatcher("/CRUDErrorPage.jsp").forward(req, resp);
        }

    }
}
