package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet pour afficher des restaurants
 */
@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String contextPath = request.getContextPath();

        try (PrintWriter out = response.getWriter()) {
            // Display all request headers
            out.println("<html><head><title>Restaurant Information</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + contextPath + "/css/login.css\">");
            out.println("</head><body>");
            out.println("<h1>Request Headers</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Header</th><th>Value</th></tr>");

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                out.println("<tr><td>" + headerName + "</td><td>" + headerValue + "</td></tr>");
            }

            out.println("</table>");
            out.println("<hr>");

            if (id == null || id.isEmpty()) {
                // Display the list of restaurants
                out.println("<h2>List of Restaurants</h2>");
                out.println("<ul>");
                out.println("<li><a href=\"" + contextPath + "/restaurant?id=1\">La Bella Italia</a> - Italienne</li>");
                out.println("<li><a href=\"" + contextPath + "/restaurant?id=2\">Le Gourmet</a> - Française</li>");
                out.println("<li><a href=\"" + contextPath + "/restaurant?id=3\">Sakura</a> - Japonaise</li>");
                out.println("</ul>");
            } else {
                // Display information for a specific restaurant
                String restaurantName;
                String cuisineType;

                switch (id) {
                    case "1":
                        restaurantName = "La Bella Italia";
                        cuisineType = "Italienne";
                        break;
                    case "2":
                        restaurantName = "Le Gourmet";
                        cuisineType = "Française";
                        break;
                    case "3":
                        restaurantName = "Sakura";
                        cuisineType = "Japonaise";
                        break;
                    default:
                        restaurantName = "Inconnu";
                        cuisineType = "Inconnu";
                        break;
                }

                out.println("<h2>Restaurant Details</h2>");
                out.println("<p><strong>Name:</strong> " + restaurantName + "</p>");
                out.println("<p><strong>Cuisine:</strong> " + cuisineType + "</p>");
                out.println("<hr>");
                out.println("<a href=\"" + contextPath + "/restaurant\">Back to List</a>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
            // Handle errors
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("<html><body><h1>une erreur est apparue lors de la requête</h1></body></html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet qui retourne les infos de restaurant et l'entête de la requête.";
    }
}