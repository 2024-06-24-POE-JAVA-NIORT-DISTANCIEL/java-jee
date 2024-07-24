package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.bigcorp.booking.classesStore.Magasin;
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
@WebServlet("/serviette/home")
public class ServietteHome extends HttpServlet {
	
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
                    "<title>Magasin de serviette</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">"
                    + "<h2> Bienvenue dans ce super magasin de serviette ! </h2>");

            out.println("<a href='../serviette/panier'> Accèder au panier </a>");        
            
            HttpSession session = request.getSession();

            Magasin towelStore = (Magasin) session.getAttribute("magasin");
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

            if (towelStore == null && utilisateur == null) {
                towelStore = new Magasin();
                utilisateur = new Utilisateur();

                Serviette servietteBernard = new Serviette("Bernard");
                Serviette servietteGulvial = new Serviette("Gulvial");
                Serviette serviettePorto = new Serviette("Porto");
    
                towelStore.addArticle(servietteBernard);
                towelStore.addArticle(servietteGulvial);
                towelStore.addArticle(serviettePorto);
            }

            session.setAttribute("magasin", towelStore);
            session.setAttribute("utilisateur", utilisateur);

            out.println("<div class='articles'>");

            for (Serviette maServiette : towelStore.getArticles()) {
                out.println("<article>");

                out.println("<p> Nom : <a href='../serviette/detail?id=" + maServiette.getId() + "'>" + maServiette.getNom() + "</a> </p>");

                out.println("</article>");
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