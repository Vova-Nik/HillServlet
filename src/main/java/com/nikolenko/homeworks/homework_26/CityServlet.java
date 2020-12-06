package com.nikolenko.homeworks.homework_26;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/city", "/city?"})
public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        String serviceMessage = "";
        if (id != null) {
            serviceMessage = DbDataProvider.deleteCity(Integer.parseInt(id)) + " has been deleted";
        }

        StringBuilder cities = DbDataProvider.getCitiesHTML();
        if (cities.length() == 0) {
            serviceMessage = "<h2>Sorry. Database inaccessible, try again later</h2>";
            return;
        }

        req.setAttribute("service_message", serviceMessage);
        req.setAttribute("cities", cities);

        req.getServletContext().getRequestDispatcher("/city.jsp").forward(req, resp);
    }

}
