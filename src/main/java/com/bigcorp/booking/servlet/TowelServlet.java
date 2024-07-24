package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bigcorp.booking.model.Towel;

@WebServlet({"/towel/home", "/towel/detail"})
public class TowelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Liste statique des serviettes
    private List<Towel> towels = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialiser la liste des serviettes avec des données de démonstration
        towels.add(new Towel(1, "Serviette de Bain", "Serviette douce pour la baignoire.", 19.99));
        towels.add(new Towel(2, "Serviette de Plage", "Grande serviette pour la plage.", 29.99));
        towels.add(new Towel(3, "Serviette de Sport", "Serviette absorbante pour le sport.", 15.99));
        towels.add(new Towel(4, "Serviette de Table", "Serviette pour s'essuyer la bouche.", 10.99));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/towel/home")) {
            // Afficher la liste des serviettes
            request.setAttribute("towels", towels);
            request.getRequestDispatcher("/home.html").forward(request, response);
        } else if (path.equals("/towel/detail")) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    Towel towel = findTowelById(id);
                    if (towel != null) {
                        request.setAttribute("towel", towel);
                        request.getRequestDispatcher("/detail.html").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    // Trouver une serviette par ID
    private Towel findTowelById(int id) {
        for (Towel towel : towels) {
            if (towel.getId() == id) {
                return towel;
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour afficher la liste des serviettes ou les détails d'une serviette.";
    }
}
