package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.bigcorp.booking.classesStore.Magasin;
import com.bigcorp.booking.classesStore.Serviette;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Une Servlet JEE
 */
@WebServlet("/serviette/detail")
public class ServietteDetail extends HttpServlet {
	
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
                    "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bulma@1.0.1/css/bulma.min.css'>" + 
                    "<title>Detail d'une serviette</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">");

            out.println("<a href='../serviette/home'> Revenir à la page d'acceuil </a>");

            out.println("<a href='../serviette/panier'> Accèder au panier </a>");

            out.println("<div class='servietteDetail'>");

            try {
                Integer idServietteParametre = Integer.parseInt(request.getParameter("id"));

                HttpSession session = request.getSession();
                Magasin towelStore = (Magasin) session.getAttribute("magasin");

                Serviette maServiette = towelStore.getArticles().get((idServietteParametre - 1));

                session.setAttribute("currentTowel", maServiette);

                out.println("<script> let addArticle = async () => { await fetch('http://localhost:8080/booking-jee-wildfly/serviette/ajout', { method:'GET', headers: { 'Access-Control-Allow-Origin':'*' } }); }; </script>");

                out.println("<button class='button is-primary' id='buttonPanier' onClick='addArticle()'> Ajouter au panier </button>");

                out.println("<article>");
                    
                out.print("<h2 class='title is-2'> ID : " + maServiette.getId() + " </h2>");

                out.print("<p> Nom : " + maServiette.getNom() + " </p>");

                out.println("</article>");
            }
            catch (Exception e) {
                response.setStatus(404);
                out.print("<p style='color: red'> Désolé, le produit n'a pas été trouvé </p>");
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