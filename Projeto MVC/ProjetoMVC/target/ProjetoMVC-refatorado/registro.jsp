<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Registro</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Cadastro de usuário</h1>

<form action="<%= request.getContextPath() %>/registro" method="post">
  <input type="text" name="nickname" placeholder="Nickname" required />
  <input type="email" name="email" placeholder="Email" required />
  <input type="password" name="senha" placeholder="Senha" required />
  <button type="submit">Salvar</button>
</form>

<p><a href="<%= request.getContextPath() %>/login.jsp">Ir para login</a></p>
</body>
</html>