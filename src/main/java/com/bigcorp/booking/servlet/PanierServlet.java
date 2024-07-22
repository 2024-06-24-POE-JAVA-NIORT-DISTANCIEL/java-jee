package com.bigcorp.booking.servlet;

import com.bigcorp.booking.model.Serviette;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Une Servlet JEE
 */
@WebServlet("/serviette/panier")
public class PanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Récupérer le panier depuis la session
        HttpSession session = request.getSession();
        List<Serviette> panier = (List<Serviette>) session.getAttribute("panier");

        // Si le panier n'existe pas, créer un nouveau panier
        if (panier == null) {
            panier = new ArrayList<>();
            session.setAttribute("panier", panier);
        }

        // Construction de la réponse HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Panier </title>");
        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Votre Panier</h1>");

        if (panier.isEmpty()) {
            out.println("<p>Votre panier est vide.</p>");
        } else {
            out.println("<ul>");
            for (Serviette serviette : panier) {
                out.println("<li>" + serviette.getName() + "</li>");
            }
            out.println("</ul>");
            out.println("<form action='panier' method='post'>");
            out.println("<button type='submit'>Vider le Panier</button>");
            out.println("</form>");
        }

        out.println("<a href='home'>Home</a>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
