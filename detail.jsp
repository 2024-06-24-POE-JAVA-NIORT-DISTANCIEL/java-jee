<html>
<head>
    <title>Détail de la Serviette</title>
</head>
<body>
    <h1>Détailde la Serviette</h1>
    <p>Nom : ${serviette.nom}</p>
    <p>Stock : ${serviette.stock}</p>
    <form action="${pageContext.request.contextPath}/serviette/panier" method="post">
        <input type="hidden" name="id" value="${serviette.id}">
        <button type="submit" <% if (serviette.stock == 0) { %>disabled<%} %>>
            Ajouter au panier
        </button>
    </form>
    <a href="${pageCntext.request.contextPath}/serviette/home">Retour à l'acceuil</a>
    <a href="${pageContext.request.contextPath}/serviette/panier">Voir le panier</a>
</body>
</html>