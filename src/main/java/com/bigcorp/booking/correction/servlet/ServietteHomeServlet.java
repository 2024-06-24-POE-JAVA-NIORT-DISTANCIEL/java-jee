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
 * Affiche la page d'accueil de serviette
 */
@WebServlet("/serviette/home")
public class ServietteHomeServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        //Première chose, on initialise le stock que l'on
        //met dans le scope application (si ce n'est déjà fait).
        Map<Integer, Serviette> stock = Stock.getStock(request.getServletContext());

        try (PrintWriter out = response.getWriter()) {
            out.print("""
                    <!DOCTYPE html>
                        <html>
                            <head>
                               <title>Magasin de serviettes : Page d'accueil</title>
                            </head>
                            <body>
                                <h1>Bienvenue dans le magasin de serviettes</h1>
                                <h2>Voici les produits actuellement en stock : </h2>
                                <table>
                                    <tr>
                                        <th>Id serviette</th>
                                        <th>Nom serviette</th>
                                    </tr>
                    """);

            for(Integer id : stock.keySet()){
                Serviette serviette = stock.get(id);
                out.println("<tr><td><a href=\"./detail?id="+id+"\">" + id + "</a></td><td>"+ serviette.getNom() + "</td></tr>");
            }

            out.print("""
                                </table>
                                <div><a href=\"./home\">Accueil</a></div><div><a href=\"panier\">Panier</a></div>
                            </body>
                        </html>
                        """);
        }
    }


}
