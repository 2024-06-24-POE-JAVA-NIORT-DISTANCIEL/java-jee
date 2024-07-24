package com.bigcorp.booking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/text")
public class TextServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String text = request.getParameter("text");
            out.println("<p> Le text : " + text + "</p>");

            String cookie = request.getHeader("Cookie");
            out.println("<p>" + cookie + "</p>");

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                out.println("<p><b>" + headerName + ":</b> " + headerValue + "</p>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Response servlet text";
    }
}
