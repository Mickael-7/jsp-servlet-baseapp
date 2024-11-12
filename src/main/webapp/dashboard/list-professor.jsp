<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.ProfessorService" %>
<%@ page import="br.mendonca.testemaven.services.dto.ProfessorDTO" %>

<%
  if (session.getAttribute("user") != null) {
    ProfessorService professorService = new ProfessorService();
    List<ProfessorDTO> professores = professorService.listAllProfessors();
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Criar Professor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

  <nav class="navbar navbar-expand-lg bg-body-terciary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
           <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
                              <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
                              <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>

          <li class="nav-item"><a class="nav-link" href="/dashboard/list-professor.jsp">Registrar professor</a></li>
        </ul>
        <span class="navbar-text">
          <a class="btn btn-success" href="/auth/logoff">Logoff</a>
        </span>
      </div>
    </div>
  </nav>

  <div class="container mt-4">
    <h1>Criar Professor</h1>
    <form method="post" action="/dashboard/register">
      <div class="mb-3">
        <label for="name" class="form-label">Nome do Professor</label>
        <input type="text" class="form-control" id="name" name="nome" required>
      </div>
      <div class="mb-3">
        <label for="idade" class="form-label">Idade</label>
        <input type="number" class="form-control" id="idade" name="idade" required min="0">
      </div>
      <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="estaPresente" name="estaPresente">
        <label class="form-check-label" for="estaPresente">Está Presente?</label>
      </div>
      <button type="submit" class="btn btn-primary">Criar Professor</button>
    </form>

    <!-- Lista de Professores Cadastrados -->
    <div class="mt-5">
      <h2>Professores Cadastrados</h2>
      <ul class="list-group">
        <% for (ProfessorDTO professor : professores) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= professor.getName() %> |
            <strong>Idade:</strong> <%= professor.getIdade() %> |
            <strong>Está Presente?:</strong> <%= professor.isEstaPresente() ? "Sim" : "Não" %>
          </div>
          <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#detalhesModal<%= professor.getUuid() %>">
            Ver Detalhes
          </button>
        </li>

        <!-- Modal para detalhes do professor -->
        <div class="modal fade" id="detalhesModal<%= professor.getUuid() %>" tabindex="-1" aria-labelledby="detalhesModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="detalhesModalLabel">Detalhes do Professor</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p><strong>UUID:</strong> <%= professor.getUuid() %></p>
                <p><strong>Nome:</strong> <%= professor.getName() %></p>
                <p><strong>Idade:</strong> <%= professor.getIdade() %> anos</p>
                <p><strong>Está Presente?:</strong> <%= professor.isEstaPresente() ? "Sim" : "Não" %></p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </ul>
    </div>
  </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
