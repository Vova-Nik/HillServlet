package com.nikolenko.homeworks.controller;

import com.nikolenko.homeworks.service.CountryDataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/countries/*")
public class SelectedCountriServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        String pathInfo = req.getPathInfo();
        String country = CountryDataService.parsePathInfo(pathInfo);
        try(PrintWriter writer = resp.getWriter()) {
            writer.println(country);
            writer.flush();
        } catch(IOException e) {
           e.printStackTrace();
        }
    }
}
