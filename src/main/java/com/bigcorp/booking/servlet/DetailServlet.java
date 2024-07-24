package com.bigcorp.booking.servlet;

import com.bigcorp.booking.model.tpfinal.Article;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

@WebServlet("/serviette/detail")
public class DetailServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext servletContext = request.getServletContext();

        Integer id = Integer.valueOf(request.getParameter("id"));
        TreeMap<Integer, Article> magasin = (TreeMap<Integer, Article>) servletContext.getAttribute("magasin");

        Article articleCourant = new Article(null, null);
        boolean bArticleTrouve = false;
        for (Article objet : magasin.values()){
            if (objet.getID() == id){
                articleCourant = objet;
                bArticleTrouve = true;
                break;
            }
        }
        try (PrintWriter out = response.getWriter()) {
            if (bArticleTrouve) {
                out.println("<h1>Voici le detail de l'article : " + id + "</h1>");
                out.println("<p>"+ articleCourant.getDetail() + "</p>");
                out.println("<a href=./home>Page accueil</a>");
            } else {
                out.println("<h1>Nous sommes désolés, nous n'avons pas de détails sur cet article");
                out.println("<img src='http://localhost:8080/booking-jee-wildfly/img/mandala.jpg' />");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";
    }
}

