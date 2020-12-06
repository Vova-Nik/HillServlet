package com.nikolenko.homeworks.homework_26;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/country")
public class CountryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType("countries.csv");
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        resp.setContentType(mimeType);
        StringBuilder countriesCSV = DbDataProvider.getCountriesCSV();
        resp.setContentLength(countriesCSV.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "countries.csv");
        resp.setHeader(headerKey, headerValue);
        OutputStream outStream = resp.getOutputStream();
        outStream.write(countriesCSV.toString().getBytes());
        outStream.close();
    }
}
