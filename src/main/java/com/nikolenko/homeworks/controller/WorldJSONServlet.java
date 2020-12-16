package com.nikolenko.homeworks.controller;

import com.nikolenko.homeworks.service.CityDataService;
import com.nikolenko.homeworks.service.CountryDataService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/index")
public class WorldJSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String result;
        Enumeration<String> entity = req.getHeaders("entity");
        String en = entity.nextElement();
        PrintWriter printWriter = resp.getWriter();
        switch (en) {
            case ("cities"):
                result = CityDataService.getAllJson();
                break;
            case ("countries"):
                result = CountryDataService.getAllJson();
                break;
            default:
                result = "{\"Bad request\":\"Unable to process\"}";
        }
        printWriter.print(result);
        printWriter.flush();
    }
}
