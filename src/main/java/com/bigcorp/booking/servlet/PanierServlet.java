package com.bigcorp.booking.servlet;

import com.bigcorp.booking.model.ServietteType;
import com.bigcorp.booking.model.Panier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/serviette/panier")
public class PanierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        List<ServietteType> articles = panier.getArticles();

        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Panier</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Votre Panier</h1>");

            if (articles.isEmpty()) {
                out.println("<p>Votre panier est vide.</p>");
            } else {
                out.println("<ul>");
                for (ServietteType article : articles) {
                    out.println("<li>" + article.getName() + " - " + article.getPrix() + "€</li>");
                }
                out.println("</ul>");
            }

            out.println("<a href='./home'>Retour à la page d'accueil</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Panier panier = (Panier) session.getAttribute("panier");

        if (panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        String articleName = request.getParameter("articleName");
        int prix = Integer.parseInt(request.getParameter("prix"));

        ServietteType article = new ServietteType(2, "Sanctuaire", 20,"h4h5",35, "coton biologique, 75cm");
        ;
        article.setName(articleName);
        article.setPrix(prix);

        panier.addArticle(article);

        response.sendRedirect(request.getContextPath() + "/serviette/panier");
    }
}