package com.bigcorp.booking.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class GreetingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setBufferSize(8192);

        // Le out permet d'écrire dans une page html grâce au request et response
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>"
                    + "<head><title>Booking</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">"
                    + "<h2>hello world ! ! ! </h2>");

            String username = request.getParameter("username");
            if (username != null && !username.isEmpty()) {
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher("/response");

                if (dispatcher != null) {
                    dispatcher.include(request, response);
                }
            }
            out.println("</body></html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "The Hello servlet says hello.";

    }
}
