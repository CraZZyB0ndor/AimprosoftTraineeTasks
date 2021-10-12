package com.aimprosoft.controllers;

import com.aimprosoft.commands.FrontCommand;

import com.aimprosoft.config.spring.ApplicationContextConfig;
import com.aimprosoft.config.spring.SpringWebAppInitializer;
import com.aimprosoft.exceptions.CRUDException;
import com.aimprosoft.services.impl.CommandFactory;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@WebServlet
public class MainController extends HttpServlet {

    private final CommandFactory commandFactory;

    @Override
    @RequestMapping("/*")
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
