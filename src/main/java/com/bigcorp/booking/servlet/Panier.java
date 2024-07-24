package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.bigcorp.booking.classesStore.Serviette;
import com.bigcorp.booking.classesStore.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Une Servlet JEE
 */
@WebServlet("/serviette/panier")
public class Panier extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Traite les requêtes GET /welcome 
	 */
	@Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>"
                    + "<head>"
                    + "<link rel='stylesheet' href='../style.css'/>" + 
                    "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bulma@1.0.1/css/bulma.min.css'/>" + 
                    "<title>Panier</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">");

            out.println("<a href='../serviette/home'> Revenir à la page d'acceuil </a>");

            out.println("<div class='panier'>");

            try {
                HttpSession session = request.getSession();
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

                for (Serviette maServiette : utilisateur.getPanier()) {
                    out.println("<article class='serviette'>");

                    out.print("<p> Nom : " + maServiette.getNom() + " </p>");

                    out.println("</article>");
                }

                if (utilisateur.getPanier().isEmpty()) {
                    out.print("<p> Aucune serviette dans votre panier </p>");
                }
            }
            catch (Exception e) {
                response.setStatus(404);
                out.print("<p style='color: red'> Désolé, une erreur s'est produite </p>");
            }

            out.println("</div>");

            out.println("</body></html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "The Hello servlet says hello.";

    }
}