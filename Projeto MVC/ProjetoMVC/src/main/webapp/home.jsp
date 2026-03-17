<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User usuario = (User) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Bem-vindo, <%= usuario.getNickname() %>!</h1>
<p>Email: <%= usuario.getEmail() %></p>

<p><a href="<%= request.getContextPath() %>/login.jsp">Voltar ao login</a></p>
</body>
</html>