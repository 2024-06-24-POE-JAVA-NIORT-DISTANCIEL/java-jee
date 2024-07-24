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
           ServietteType serviette1 = new ServietteType(1L,"Sépulcre",1,"h2o9",25,"coton biologique, 50cm" );
           out.println("<h1>" + serviette1.getName() + "</h1>");
           out.println("<div>" + serviette1.getDescription() + "</div>");

           ServietteType serviette2 = new ServietteType(2L, "Sanctuaire", 20,"h4h5",35, "coton biologique, 75cm");
           out.println("<h1>" + serviette2.getName() + "</h1>");
           out.println("<div>" + serviette2.getDescription() + "</div>");



           out.println("<a href='#'>Retour à la page d'accueil</a>");
       }
       
   }
}


