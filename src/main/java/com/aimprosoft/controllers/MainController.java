package com.aimprosoft.controllers;

import com.aimprosoft.commands.FrontCommand;

import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MainController extends HttpServlet {

    private CommandFactory commandFactory;

    @Autowired
    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

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
