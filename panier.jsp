<html>
<head>
    <title>Votre Panier</title>
</head>
<body>
    <h1> Votre Panier</h1>
    <ul>
        <c:forEach var="serviette" items="${panier.articles}">
            <li>${serviette.nom}</li>
        </c:forEach>
    </ul>
    <a href="${pageContext.request.contextPath}/serviette/home">Retour à l'acceuil</a>
</body>
</head>
