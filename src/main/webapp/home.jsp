<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*" %>
<html>
<head>
<title>Home JSP</title>
</head>

<body>
 <h1>Bienvenue dans notre magasin </h1>
    <ul>
        <c:forEach var="serviette" items="${serviettes}">
            <li><a href="detail?id=${serviette.id}">${serviette.name}</a></li>
        </c:forEach>
    </ul>
    <a href="panier">View Cart</a>
</body>
</html>