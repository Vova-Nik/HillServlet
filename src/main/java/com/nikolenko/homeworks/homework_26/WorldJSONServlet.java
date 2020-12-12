package com.nikolenko.homeworks.homework_26;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/json")
public class WorldJSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getServletContext().getRequestDispatcher("/world.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder result = new StringBuilder();
        Enumeration<String> operation = req.getHeaders("operation");
        String op = operation.nextElement();
        if(op.contains("cities")){
             result = DbDataProvider.getCitiesGSON();
        }
        if(op.contains("countries")){
            result = DbDataProvider.getCountriesJGSON();
        }
        if(op.contains("statistics")){
            result = DbDataProvider.getStatJGSON();
        }
        printWriter.print(result);
        printWriter.flush();
    }
}

