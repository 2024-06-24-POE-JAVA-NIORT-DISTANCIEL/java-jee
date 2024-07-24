package com.bigcorp.booking.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bigcorp.booking.model.User;

@WebServlet("/towel/signup")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple validation
        if (username != null && password != null) {
            User user = new User(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            response.sendRedirect("signup?error=1");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour l'inscription des utilisateurs.";
    }
}
