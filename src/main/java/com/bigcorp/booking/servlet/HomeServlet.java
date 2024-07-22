package com.bigcorp.booking.servlet;
import com.bigcorp.booking.model.Serviette;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Une Servlet JEE
 */
@WebServlet("/serviette/home")

public class HomeServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private List<Serviette> serviettes = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        serviettes.add(new Serviette(" ServietteQualité1", 1));
        serviettes.add(new Serviette("ServietteQualité2", 2));
        serviettes.add(new Serviette("ServietteQualité3", 3));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Stocker la liste les articles dans une session
        HttpSession session = request.getSession();
        session.setAttribute("serviettes", serviettes);

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Home </title>");
//        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Bienvenu dans notre magasin</h1>");
        out.println("<ul>");

        for (Serviette serviette : serviettes) {
            out.println("<li><a href='detail?id=" + serviette.getId() + "'>" + serviette.getName() + "</a></li>");
        }

        out.println("</ul>");
        out.println("<a href='panier'>panier</a>");
        out.println("</body>");
        out.println("</html>");
    }

    }
