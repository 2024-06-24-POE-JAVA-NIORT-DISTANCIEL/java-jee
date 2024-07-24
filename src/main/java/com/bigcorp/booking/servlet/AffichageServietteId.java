package com.bigcorp.booking.servlet;

import com.bigcorp.booking.dao.ServietteTypeDao;
import com.bigcorp.booking.model.ServietteType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/serviette/detail")
public class AffichageServietteId extends HttpServlet {

   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
       try(PrintWriter out = response.getWriter()) {
           String nomProduit = request.getParameter("nom");
           out.println("<h1> Serviette " + nomProduit + "</h1>");

           out.println("<a href='./welcome'>Retour à la page d'accueil</a>");
       }
       
   }
}


