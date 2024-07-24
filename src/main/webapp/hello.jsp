<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.*,java.util.*" %>
<%@ page import="com.bigcorp.booking.model.Personnage" %>
<html>
<head>
<title>JSP TP</title>
</head>
<% String message = "Bonjour à tous"; %>
<body>
<h1>Bienvenue sur une JSP !!!! Je dirai : <%= message %> </h1>
<div>
    <p>Je change la JSP</p>

      <% List<Personnage> personnages = new ArrayList<>();
        personnages.add(new Personnage("Legolas", "l'elfe"));
        personnages.add(new Personnage("Aragorn", "l'humain"));
        personnages.add(new Personnage("Gimli", "le nain"));
      %>

    <% for (Personnage perso: personnages) { %>
        <p>Nom et prénom: <%= perso.getNom() %> <%= perso.getPrenom() %></p>
    <% } %>

    <% for (int i = 1; i < 2; i++) { %>
        <p>Numéro: <%= i %></p>
    <% } %>
</div>
</body>
</html>
