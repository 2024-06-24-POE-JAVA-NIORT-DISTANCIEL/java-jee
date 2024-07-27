/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.bigcorp.booking.correction.servlet;


import com.bigcorp.booking.correction.servlet.model.Serviette;
import com.bigcorp.booking.correction.servlet.model.Stock;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 * Affiche le détail d'une serviette. ATtend un id en paramètre d'URL
 */
@WebServlet("/serviette/detail")
public class ServietteDetailServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {


        String idAsString = request.getParameter("id");

        if (idAsString == null) {
            reponse404(response);
            return;
        }

        Integer id = null;
        try {
            id = Integer.parseInt(idAsString);
        } catch (NumberFormatException nfe) {
            reponse404(response);
            return;
        }


        Map<Integer, Serviette> stock = Stock.getStock(request.getServletContext());
        Serviette serviette = stock.get(id);
        if (serviette == null) {
            reponse404(response);
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            out.print("""
                    <!DOCTYPE html>
                        <html>
                            <head>
                               <title>Magasin de serviettes : page de détail</title>
                            </head>
                            <body>
                                <h1>Détail d'une serviette</h1>
                                <h2>Voici les détails de l'article : </h2>
                    """);


            out.println("<span>Id : " + id + "</span><span> Nom : " + serviette.getNom() + "</span>");
            out.println("<a href=\"./ajout-panier?id=" + id + "\">Ajouter au panier</a>");


            out.print("""
                            <div><a href="./home">Accueil</a></div><div><a href="panier">Panier</a></div>
                        </body>
                    </html>
                    """);
        }
    }

    private void reponse404(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.print("""
                    <!DOCTYPE html>
                        <html>
                            <head>
                               <title>Magasin de serviettes : page d'erreur</title>
                            </head>
                            <body>
                                <h1>Erreur 404</h1>
                            </body>
                        </html>
                    """);
            response.setStatus(404);
        }
    }

}
