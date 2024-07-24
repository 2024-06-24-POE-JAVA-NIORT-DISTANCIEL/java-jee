<html>
<head>
    <title> Acceuil du Magasin de Serviettes</title>
</head>
<body>
    <h1>Bienvenue au Magasin de Serviettes</h1>
    <ul>
    <c:forEach var="serviette" items="${serviettes}">
       <li>
            <a href="${pageContent.request.contextPath}/serviette/detail?id=${serviette.id}">
                ${serviette.nom}
            </a>
       </li>
       <a href="${pageContext.request.contextPath}/serviette/panier">Voir le panier</a>
</body>
</html>