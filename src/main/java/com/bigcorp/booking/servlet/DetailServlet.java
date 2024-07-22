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
import java.util.List;

/**
 * Une Servlet JEE
 */
@WebServlet("/serviette/detail")

public class DetailServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Récupérer l'ID de la serviette
        int id = Integer.parseInt(request.getParameter("id"));

        // Récupérer la liste des serviettes depuis la session
        HttpSession session = request.getSession();
        List<Serviette> serviettes = (List<Serviette>) session.getAttribute("serviettes");

        Serviette serviette = null;

        // Recherche de la serviette par ID
        for (Serviette s : serviettes) {
            if (s.getId() == id) {
                serviette = s;
                break;
            }
        }

        if (serviette == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Construction de la réponse HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Detail - Article</title>");
//        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + serviette.getName() + "</h1>");
        out.println("<form action='panier' method='post'>");
        out.println("<input type='hidden' name='id' value='" + serviette.getId() + "'>");
        out.println("<button type='submit'>Ajouter au panier</button>");
        out.println("</form>");
        out.println("<a href='home'>Home</a>");
        out.println("<a href='panier'>panier</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
