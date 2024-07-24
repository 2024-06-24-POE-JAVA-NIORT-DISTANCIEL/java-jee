package com.bigcorp.booking.servlet;
import com.bigcorp.booking.model.ServietteType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@WebServlet("/serviette/detail")public class AffichageServietteId
        extends HttpServlet {
    private List<ServietteType> serviettes;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialiser une liste de serviettes pour simuler une base de données
        serviettes = new ArrayList<>();
        serviettes.add(new ServietteType(1, "Sépulcre", 1, "h2o9", 25, "coton biologique, 50cm"));
        serviettes.add(new ServietteType(2, "Sanctuaire", 20, "h4h5", 35, "coton biologique, 75cm"));
        serviettes.add(new ServietteType(3, "Crypte", 15, "a1b2", 45, "coton biologique, 95cm"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is missing");
            return;
        }
        int id;
        try {
            id = parseInt(idParam);
            System.out.println(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format");
            return;
        }
        ServietteType serviette = findServietteById(id);
        if (serviette == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Serviette not found");
            return;         }
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>" + serviette.getName() + "</h1>");
            out.println("<div>" + serviette.getDescription() + "</div>");
            out.println("<a href='/'>Retour à la page d'accueil</a>");
            out.println("</body></html>"); }
    }

    private ServietteType findServietteById(int id) {
        for (ServietteType serviette : serviettes) {
            if (Objects.equals(serviette.getId(), id)) {
                return serviette;
            }
        } return null;
    }
}

