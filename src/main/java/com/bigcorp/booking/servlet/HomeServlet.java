package com.bigcorp.booking.servlet;

import com.bigcorp.booking.model.TpFinal.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

@WebServlet("/serviette/home")
public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Article ar1 = new Article("Serviette de Bain Luxueuse", " Cette serviette de bain en coton égyptien offre une douceur exceptionnelle et une grande absorption, " +
                "parfaite pour une expérience spa à domicile. Taille généreuse pour un confort optimal.");
        Article ar2 = new Article("Serviette de Plage à Rayures", "Idéale pour les journées ensoleillées, " +
                "cette serviette de plage à rayures vives est en coton velours absorbant, douce au toucher et résistante au sable.\n");
        Article ar3 = new Article("Serviette de Voyage Microfibre", "Légère et compacte, cette serviette en microfibre sèche rapidement, " +
                "parfait pour les voyageurs. Absorbe six fois son poids en eau et se range facilement.");
        Article ar4 = new Article("Serviette de Sport Antibactérienne", "Conçue pour les athlètes, " +
                "cette serviette de sport est en microfibre avec traitement antibactérien, empêchant les mauvaises odeurs et séchant rapidement après utilisation.");
        Article ar5 = new Article("Serviette de Plage Ronde Mandala", "Dotée d'un magnifique motif mandala, cette serviette de plage ronde est en coton doux, " +
                "idéale pour les pique-niques ou se détendre au soleil.");
        Article ar6 = new Article("Serviette à Capuche pour Enfants", "Cette serviette à capuche en coton doux et absorbant est parfaite pour les enfants après le bain ou la piscine. " +
                "Motifs animaux amusants pour les petits.");
        Article ar7 = new Article("Serviette de Sauna en Lin", "Fabriquée en lin naturel, cette serviette de sauna est légère, respirante et sèche rapidement, " +
                "idéale pour une utilisation dans les saunas ou hammams.");
        Article ar8 = new Article("Serviette de Bain en Bambou", " Ultra-douce et écologique, " +
                "cette serviette de bain en fibres de bambou est naturellement antibactérienne et très absorbante, offrant une sensation de luxe à chaque utilisation.");
        Article ar9 = new Article("Serviette de Plage XL", " Parfaite pour deux personnes, " +
                "cette serviette de plage extra-large en coton épais est idéale pour les journées à la plage, offrant confort et espace.");
        Article ar10 = new Article("Serviette de Bain à Séchage Rapide", "Conçue pour un usage quotidien, " +
                "cette serviette en microfibre sèche rapidement et reste douce après de nombreux lavages, idéale pour les foyers occupés.");

        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Nous vous souhaitons la bienvenue</h1>");
            out.println("<h3 style='text-align: center;'>Des serviettes comme vous n'en n'avez jamais vu !</h3>");
            out.println("<table border='1' style='border-color: #f2eedf; width: 50%; margin: 0 auto; margin-bottom: 10rem; border-collapse: collapse; color: #fff3b5;'>");
            out.println("<tr><th style='padding: 8px; background-color: #333;'>Modèles </th><th style='padding: 8px; background-color: #333;'>Description</th></tr>");

            TreeMap<Integer, Article> magasin = new TreeMap<>();
            magasin.put(ar1.getID(), ar1);
            magasin.put(ar2.getID(), ar2);
            magasin.put(ar3.getID(), ar3);
            magasin.put(ar4.getID(), ar4);
            magasin.put(ar5.getID(), ar5);
            magasin.put(ar6.getID(), ar6);
            magasin.put(ar7.getID(), ar7);
            magasin.put(ar8.getID(), ar8);
            magasin.put(ar9.getID(), ar9);
            magasin.put(ar10.getID(), ar10);

            for (Article objet : magasin.values()) {
                out.println("<tr><td style='padding: 8px;'>" + objet.getName() + "</td><td style='padding: 8px;'>" + "<a href=./detail>Detail article</a>" + "</td></tr>");
            }
            out.println("</table>");
        }
    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";
    }
}