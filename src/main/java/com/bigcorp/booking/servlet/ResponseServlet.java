package com.bigcorp.booking.servlet;

import com.bigcorp.booking.model.Counter;
import com.sun.net.httpserver.HttpContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Counter count = new Counter(0.0, 0.0);

        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");

            HttpContext context = (HttpContext) request.getServletContext();
            String nameApp = context.getPath();
            nameApp.getBytes(StandardCharsets.UTF_8);

            Double incrementer = count.incrementer();
            HttpSession session = request.getSession();

            if (session.getAttribute("compteur") == null) {
                session.setAttribute("compteur", incrementer);
            } else {
                session.setAttribute("compteur", count);
            }
            out.println("<h2>Salut, " + username + "!</h2>");
            out.println("<p>Le compteur : " + incrementer + "!<p>");
            out.println("<p>Le nombre aléatoire : " + count.getAleatoire() + "!<p>");
            out.println("<p>Le serveur " + context + "!<p>");

        }
    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";
    }
}