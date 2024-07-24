package com.bigcorp.booking.servlet;

import java.io.IOException;

import com.bigcorp.booking.classesStore.Serviette;
import com.bigcorp.booking.classesStore.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/serviette/ajout")
public class BoutonAjout extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try {
            HttpSession session = request.getSession();

            Serviette maServiette = (Serviette) session.getAttribute("currentTowel");
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

            utilisateur.addToPanier(maServiette);
        }
        catch (Exception e) {
            response.setStatus(404);
        }
    }
}