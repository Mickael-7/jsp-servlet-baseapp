<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<%
List<UserDTO> lista = (List<UserDTO>) request.getAttribute("lista");
if (lista == null) {
    lista = new ArrayList<>();
}

String searchQuery = (String) request.getAttribute("searchQuery");
if (searchQuery == null) {
    searchQuery = "";
}
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerência de Configuração</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
  </head>
  <body class="d-flex align-items-center py-4 bg-body-tertiary">
    <main class="w-100 m-auto form-container">
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/estrelas.jsp">Estrelas</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/estrelasDeletadas.jsp">Estrelas deletadas</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/planetas">Planetas</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/planetas?viewDeleted=true">Planetas deletados</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/list-galaxias.jsp">Galaxias</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/galaxiasDeletadas.jsp">Galaxias deletadas</a></li>
              <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
            </ul>
            <span class="navbar-text">
              <a class="btn btn-success" href="/auth/logoff">Logoff</a>
            </span>
          </div>
        </div>
      </nav>

      <h1 class="h3 mb-3 fw-normal">Usuários</h1>

      <!-- Formulário de Pesquisa -->
      <form method="get" action="/dashboard/users" class="mb-3">
        <div class="input-group">
          <input type="text" name="name" class="form-control" placeholder="Digite o nome do usuário para buscar..." value="<%= searchQuery %>">
          <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
      </form>

      <!-- Tabela de Resultados -->
      <table class="table">
        <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">Nome</th>
            <th scope="col">E-mail</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <%
          if (lista.isEmpty()) {
          %>
          <tr>
            <td colspan="4">Nenhum usuário encontrado.</td>
          </tr>
          <%
          } else {
              for (UserDTO user : lista) {
          %>
          <tr>
            <td>Editar</td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td>Apagar</td>
          </tr>
          <%
              }
          }
          %>
        </tbody>
      </table>
    </main>
  </body>
</html>
