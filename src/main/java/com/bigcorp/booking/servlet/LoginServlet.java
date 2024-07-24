package com.bigcorp.booking.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bigcorp.booking.model.User;

@WebServlet("/towel/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple validation
        if (username != null && password != null) {
            // Normally, you would check against a database
            User user = new User(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            response.sendRedirect("login?error=1");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la connexion des utilisateurs.";
    }
}
