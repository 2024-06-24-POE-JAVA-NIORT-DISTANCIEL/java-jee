package com.bigcorp.booking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/serviette/detail")
public class DetailServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Voici le detail de l'article</h1>");
            out.println("<table border='1' style='border-color: #f2eedf; width: 50%; margin: 0 auto; margin-bottom: 10rem; border-collapse: collapse; color: #fff3b5;'>");
            out.println("<tr><th style='padding: 8px; background-color: #333;'>Description</th>");

            out.println("<tr><td style='padding: 8px;'>" + "");
            out.println("<tr><td style='padding: 8px;'>" + "<a href=./home>Page accueil</a>");
        }
    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";
    }
}

