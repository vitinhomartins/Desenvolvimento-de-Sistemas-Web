<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Login</h1>

<%-- Mensagem opcional por querystring: ?erro=1 --%>
<%
    String erro = request.getParameter("erro");
    if ("1".equals(erro)) {
%>
<p style="color: red;">Email ou senha inválidos</p>
<%
    }
%>

<form action="<%= request.getContextPath() %>/login" method="post">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="senha" placeholder="Senha" required>
    <button type="submit">Entrar</button>
</form>

<p><a href="<%= request.getContextPath() %>/registro.jsp">Criar conta</a></p>
</body>
</html>