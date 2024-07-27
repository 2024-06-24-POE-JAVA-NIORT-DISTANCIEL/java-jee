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


import com.bigcorp.booking.correction.servlet.model.Panier;
import com.bigcorp.booking.correction.servlet.model.Serviette;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.bigcorp.booking.correction.servlet.ServietteAddToBasketServlet.PANIER_NAME;

/**
 * Affiche le contenu du panier
 */
@WebServlet("/serviette/panier")
public class ServiettePanierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        //Récupération du panier, ou création au besoin
        Panier panier = (Panier) request.getSession().getAttribute(PANIER_NAME);
        if(panier == null){
            panier = new Panier();
            request.getSession().setAttribute(PANIER_NAME, panier);
        }

        try (PrintWriter out = response.getWriter()) {
            out.print("""
                    <!DOCTYPE html>
                        <html>
                            <head>
                               <title>Magasin de serviettes : panier</title>
                            </head>
                            <body>
                                <h1>Panier</h1>
                                <h2>Votre panier : </h2>
                                 <table>
                                    <tr>
                                        <th>Id serviette</th>
                                        <th>Nom serviette</th>
                                    </tr>
                    """);
            for(Serviette serviette : panier.getAll()){
                out.println("<tr><td>" + serviette.getId() + "</td><td>"+ serviette.getNom() + "</td></tr>");
            }


            out.print("""
                            </table>
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
